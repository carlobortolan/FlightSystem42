package de.tum.in.ase.eist.rest;

import de.tum.in.ase.eist.model.Note;
import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.service.PersonService;
import de.tum.in.ase.eist.util.PersonSortingOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.Endpoint;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class PersonResource {
    private final PersonService personService;
    private final List<Note> notes;
    public PersonResource(PersonService personService) {
        this.personService = personService;
        this.notes = new ArrayList<>();
    }
    // TODO endpoints
    @PostMapping("persons")
    public ResponseEntity<Person> createNote(@RequestBody Person person) {
        if (person.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personService.savePerson(person));
    }
    public Note saveNote(Note note) {
        var optionalNote = notes.stream().filter(existingNote -> existingNote.getId().equals(note.getId())).findFirst();
        if (optionalNote.isEmpty()) {
            note.setId(UUID.randomUUID());
            note.setCreationDate(Instant.now());
            notes.add(note);
            return note;
        } else {
            var existingNote = optionalNote.get();
            existingNote.setName(note.getName());
            existingNote.setContent(note.getContent());
            return existingNote;
        }
    }
    @PutMapping("persons/{personId}")
    public ResponseEntity<Person> updateNote(@RequestBody Person updatedPerson, @PathVariable("personId") UUID personId) {
        if (!updatedPerson.getId().equals(personId)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.personService.savePerson(updatedPerson));
    }

    @DeleteMapping("persons/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable("personId") UUID personId) {
        this.personService.deletePerson(personId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("persons")
    public ResponseEntity<List<Person>> getAllPersons (
            @RequestParam(value = "sortField", required = false) PersonSortingOptions.SortField sortField,
            @RequestParam(value = "sortingOrder", required = false) PersonSortingOptions.SortingOrder sortingOrder) {
        if(sortingOrder == null || sortField == null) {
            return ResponseEntity.ok(personService.getAllPersons(new PersonSortingOptions()));
        }
        return ResponseEntity.ok(personService.getAllPersons(new PersonSortingOptions(sortingOrder, sortField)));
    }

    public static void main(String[] args) {

    }
}

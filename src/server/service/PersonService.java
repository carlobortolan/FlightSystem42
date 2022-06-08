package de.tum.in.ase.eist.service;

import de.tum.in.ase.eist.model.Note;
import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.util.PersonSortingOptions;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {
  	// do not change this
    private final List<Person> persons;

    public PersonService() {
        this.persons = new ArrayList<>();
    }

    public Person savePerson(Person person) {
        int i = this.persons.size();
        var optionalPerson = persons.stream().filter(existingPerson -> existingPerson.getId().equals(person.getId())).findFirst();
        if (optionalPerson.isEmpty()) {
            person.setId(UUID.randomUUID());
            persons.add(person);
            return person;
        } else {
            var existingPerson = optionalPerson.get();
            existingPerson.setFirstName(person.getFirstName());
            existingPerson.setLastName(person.getLastName());
            existingPerson.setBirthday(person.getBirthday());
            return existingPerson;
        }
    }

    public void deletePerson(UUID personId) {
        this.persons.removeIf(person -> person.getId().equals(personId));
    }

    public List<Person> getAllPersons(PersonSortingOptions sortingOptions) {
        // TODO Part 3: Add sorting here
        if(sortingOptions.getSortingOrder().equals(PersonSortingOptions.SortingOrder.DESCENDING)) {
            switch (sortingOptions.getSortField()) {
                case ID -> this.persons.sort((a, b) -> b.getId().compareTo(a.getId()));
                case BIRTHDAY -> this.persons.sort((a, b) -> b.getBirthday().compareTo(a.getBirthday()));
                case FIRST_NAME -> this.persons.sort((a, b) -> b.getFirstName().compareTo(a.getFirstName()));
                case LAST_NAME -> this.persons.sort((a, b) -> b.getLastName().compareTo(a.getLastName()));
            }
        } else {
            switch (sortingOptions.getSortField()) {
                case ID -> this.persons.sort(Comparator.comparing(Person::getId));
                case BIRTHDAY -> this.persons.sort(Comparator.comparing(Person::getBirthday));
                case FIRST_NAME -> this.persons.sort(Comparator.comparing(Person::getFirstName));
                case LAST_NAME -> this.persons.sort(Comparator.comparing(Person::getLastName));
        }
        }
        return new ArrayList<>(this.persons);
    }

//      System.out.println("Size = " + i + "->" + this.persons.size() + " | Person = " + person.getId().toString());
//      System.out.println("TEST sortField = " + sortingOptions.getSortField());
//      System.out.println("TEST sortingOrder = " + sortingOptions.getSortingOrder());
//      public List<Person> getPersons() {return Collections.unmodifiableList(this.persons);}
//      public static void main(String[] args) {
//      PersonService service = new PersonService();
//      for(int i = 0; i < 10; i++) service.savePerson(new Person());
//      service.getAllPersons(new PersonSortingOptions());
//      for(Person p : service.getPersons()) System.out.println("Person = " + p.getId());
}


package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.service.SurveyService;

import java.util.List;
import java.util.UUID;

@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class SurveyResource {

    private final SurveyService surveyService;
    private String[] tmp2 = new String[] {"Drink1 ", "Drink2 ", "Drink3"};


    public SurveyResource(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping("surveys")
    public ResponseEntity<List<String>> getAllSurveys(@RequestParam("secret") String secret) {
        if (!"SecretKey".equals(secret)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(surveyService.getAllSurveys());
    }

    @PostMapping("surveys")
    public ResponseEntity<String> createSurvey(@RequestBody String survey) {
//        if (survey == null) {
        System.out.println("survey 2 = " + survey);
            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(surveyService.saveSurvey(survey));
    }

    @PutMapping("Surveys/{surveyId}")
    public ResponseEntity<String> updateSurvey(@RequestBody String updatedSurvey, @PathVariable("surveyId") String surveyId) {
        if (!updatedSurvey.equals(surveyId)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(surveyService.saveSurvey(updatedSurvey));
    }

    @DeleteMapping("surveys/{surveyId}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable("surveyId") String surveyId) {
        surveyService.deleteSurvey(surveyId);
        return ResponseEntity.noContent().build();
    }
}

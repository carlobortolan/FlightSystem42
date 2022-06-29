package server.service;

import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class SurveyService {
    private final List<String> surveys;

    public SurveyService() {
        this.surveys= new ArrayList<>();
    }

    public String saveSurvey(String survey) {
        var optionalSurvey = surveys.stream().filter(existingSurvey -> existingSurvey.equals(survey)).findFirst();
        if (optionalSurvey.isEmpty()) {
            surveys.add(survey);
            return survey;
        } else {
            var existingSurvey = optionalSurvey.get();
            existingSurvey = survey;
            return existingSurvey;
        }
    }

    public void deleteSurvey(String survey) {
        this.surveys.removeIf(s -> s.equals(survey));
    }

    public List<String> getAllSurveys() {
        return Collections.unmodifiableList(this.surveys);
    }
}

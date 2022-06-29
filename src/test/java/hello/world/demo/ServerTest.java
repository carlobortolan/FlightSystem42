package hello.world.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Server.class)
@ContextConfiguration(classes = Server.class)
class ServerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSurvey() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Surveys/");
        mockMvc.perform(requestBuilder).andReturn().getResponse();
        String result = mockMvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
        assertEquals("Survey1 Survey2 Survey3", result);
    }
    @Test
    public void testServiceRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ServiceRequest/");
        mockMvc.perform(requestBuilder).andReturn().getResponse();
        String result = mockMvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
        assertEquals("Drink1 Drink2 Drink3", result);
    }

}

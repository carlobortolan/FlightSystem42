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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String result = mockMvc.perform(requestBuilder).andReturn().getResponse().getContentAsString().trim();
        assertEquals("", result);
    }
    @Test
    public void testServiceRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Drinks");
        mockMvc.perform(requestBuilder).andReturn().getResponse();
        String result = mockMvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
        assertEquals("d1 - 0<br /><br />d2 - 0<br /><br />d3 - 0<br /><br />d4 - 0<br /><br />d5 - 0<br /><br />d6 - 0<br /><br />d7 - 0<br /><br />d8 - 0<br /><br />d9 - 0<br /><br />", result);
    }

}

/*
 * Copyright (c)  2022,  Carlo Bortolan, Fabian Fritz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package server;

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
        assertEquals("<html><head><style>\n" +
                "table, th, td {\n" +
                "  border:1px solid black;\n" +
                "}\n" +
                "</style>\n" +
                "</head><body>\n" +
                "<h2>Surveylogs</h2>\n" +
                "\n" +
                "<p>(1) How was your experience before departure?</p>\n" +
                "<p>(2) How would you rate the flight itself?</p>\n" +
                "<p>(3) What was your experience with our stewardess?</p>\n" +
                "<p>(4) Did your meals and drinks taste fine?</p>\n" +
                "<p>(5) How do you feel about our entertaiment system?</p>\n" +
                "\n" +
                "    \n" +
                "<table style=\"width:100%\">\n" +
                "  <tbody><tr>\n" +
                "    <th>Date</th>\n" +
                "    <th>Flightnumber</th>\n" +
                "    <th>1</th>\n" +
                "    <th>2</th>\n" +
                "    <th>3</th>\n" +
                "    <th>4</th>\n" +
                "  <th>5</th>\n" +
                "      <th>Name</th>\n" +
                "      <th>Additional text</th>\n" +
                "  </tr></tbody></table></body></html>", result);
    }
    @Test
    public void testServiceRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Drinks");
        mockMvc.perform(requestBuilder).andReturn().getResponse();
        String result = mockMvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
        assertEquals("Water - 0<br /><br />Apple juice - 0<br /><br />Coke - 0<br /><br />Hot chocolate - 0<br /><br />Coffee - 0<br /><br />Tee - 0<br /><br />Beer - 0<br /><br />Martini - 0<br /><br />Champagne - 0<br /><br />", result);
    }

}

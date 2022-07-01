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

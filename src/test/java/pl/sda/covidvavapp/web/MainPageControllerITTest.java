package pl.sda.covidvavapp.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainPageControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void shouldDisplayMainPage() throws Exception {
        //given

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/"));
        //then
        result.andExpect(model().attribute("currentDate", LocalDate.now()))
                .andExpect(content().string(containsString("Hello world!")))
                .andExpect(status().is2xxSuccessful());
    }

}

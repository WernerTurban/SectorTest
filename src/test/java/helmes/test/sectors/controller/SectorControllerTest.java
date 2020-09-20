package helmes.test.sectors.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import helmes.test.sectors.model.Sector;
import helmes.test.sectors.model.UserData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SectorControllerTest {

    @Autowired
    private MockMvc mvc;


     @Test
     public void createUser() throws Exception {
     UserData userToSave = createUserData();
     mvc.perform(MockMvcRequestBuilders
     .post("/user")
     .content(asJsonString(userToSave))
     .contentType(MediaType.APPLICATION_JSON)
     .accept(MediaType.APPLICATION_JSON))
     .andExpect(status().isOk());
     }

     @Test
     public void getSectors() throws Exception {
        mvc.perform(
        get("/sectors")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
     }


    public UserData createUserData() {
        List<Sector> sectors = new ArrayList<>();
        sectors.add(new Sector(1, "Manufacturing", 1, ""));
        return new UserData(null, "test", sectors);
    }

     //https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
     public static String asJsonString(final Object obj) {
      try {
      return new ObjectMapper().writeValueAsString(obj);
      } catch (Exception e) {
      throw new RuntimeException(e);
      }
     }
}


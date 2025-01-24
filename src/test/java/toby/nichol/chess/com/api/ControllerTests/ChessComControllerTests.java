package toby.nichol.chess.com.api.ControllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ChessComControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void playerEndpointShouldReturnPlayerOnValidRequest() throws Exception {
        this.mockMvc.perform(get("/chess-com/get-player/hikaru").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}

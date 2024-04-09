package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllAnimals_shouldReturnErrorIsNotFound_whenCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getSpecies_shouldReturnErrorBadRequest_whenCalledWithCat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals/cat"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getSpecies_shouldReturnDog_whenCalledWithDog() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals/dog"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("dog"));
    }

    @Test
    public void getAllCars_shouldReturnErrorIsNotFound_whenCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getCars_shouldReturnErrorBadRequest_whenCalledWithBmw() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/bmw"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getCars_shouldReturnPorsche_whenCalledWithPorsche() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/porsche"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("porsche"));
    }

}

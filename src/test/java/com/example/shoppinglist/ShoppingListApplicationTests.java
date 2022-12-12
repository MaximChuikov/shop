package com.example.shoppinglist;

import com.example.shoppinglist.controllers.RESTApiController;
import com.example.shoppinglist.models.Product;
import com.example.shoppinglist.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class ShoppingListApplicationTests {
    @Autowired
    private RESTApiController restApiController;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void rest() throws Exception {
        assertThat(restApiController).isNotNull();
    }

    @Test
    public void listOfProducts() throws Exception {
        this.mockMvc.perform(post("/api/products/add")
                        .contentType(APPLICATION_JSON)
                        .content("{\"productName\": \"scooter\"}"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/api/products/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("scooter")));
    }

    @Test
    public void getProductById() throws Exception {
        long id = 100L;
        Optional<Product> product = productRepository.findById(id);

        this.mockMvc.perform(get("/api/products/" + id)
                        .contentType(APPLICATION_JSON)
                        .content("{\"id\":111,\"name\":\"cucumber\",\"bought\":\"false\"}"))
                .andDo(print())
                .andExpect(content().string(containsString(product.get().getName())))
                .andExpect(status().isOk());
    }
}
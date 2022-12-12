package com.example.shoppinglist;

import com.example.shoppinglist.models.Product;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ModuleTests {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void addProduct() {
        String productTitle = "молоко";
        Product savedProduct = productService.save(productTitle);
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getName().equals(productTitle));
        Mockito.verify(productRepository, Mockito.times(1)).save(savedProduct);
    }

    @Test
    public void deleteProduct() {
        Optional<Product> product = Optional.of(new Product(3L, "ветчина", false));
        Long id = 3L;
        Mockito.doReturn(product).when(productRepository).findById(id);
        long deleteResult = productService.delete(id);
        assertThat(deleteResult).isEqualTo(3L);
    }

}
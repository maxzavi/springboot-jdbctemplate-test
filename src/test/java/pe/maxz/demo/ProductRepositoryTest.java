package pe.maxz.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import pe.maxz.demo.repository.ProductRepository;

@ContextConfiguration(classes = ProductRepository.class)
@JdbcTest
@Sql( value = {"/jdbc/schema.sql", "/jdbc/test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql( value = {"/jdbc/clean.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void get_returnProduct9(){
        System.out.println("test1");

        var product = productRepository.get("9");
        assertEquals("Product 9", product.getName());
    }

    @Test
    void get_returnNotNull() {
        System.out.println("test2");

        var product = productRepository.get("2");
        System.out.println(product);
        assertNotNull(product);
    }
}

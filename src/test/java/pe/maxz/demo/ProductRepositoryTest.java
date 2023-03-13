package pe.maxz.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.EmptyResultDataAccessException;
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
        var product = productRepository.get("9");
        assertEquals("Product 0009", product.getName());
    }

    @Test
    void get_returnNotNull() {
        var product = productRepository.get("2");
        System.out.println(product);
        assertNotNull(product);
    }
    @Test
    void getAll_returnQtyRows(){
        var products = productRepository.getAll();
        assertEquals(9, products.size());
    }
    @Test
    void get_returnNotFoundException(){
        assertThrowsExactly(EmptyResultDataAccessException.class, ()->{
            productRepository.get("099");
        });
    }
    @Test
    void get_returnException(){
        assertThrows(Exception.class, ()->{
            productRepository.get("099");
        });
    }
    @Test
    void get_NotReturnNotFoundException(){
        assertDoesNotThrow( ()->{
            productRepository.get("1");
        });
    }

}

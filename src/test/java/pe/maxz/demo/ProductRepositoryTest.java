package pe.maxz.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import pe.maxz.demo.repositoty.ProductRepository;

@ContextConfiguration(classes = ProductRepositoryTest.class)

@JdbcTest
@Sql({"/jdbc/schema.sql", "/jdbc/test-data.sql"})

public class ProductRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    void testGetAll_thenReturnCountNonZero(){
        ProductRepository productRepository= new ProductRepository();
        productRepository.setJdbcTemplate(jdbcTemplate);
        var items = productRepository.getAll();
        assertEquals(12, items.size());
    }
}

package pe.maxz.demo.repositoty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pe.maxz.demo.model.Product;

@Repository
public class ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    
    public Product get(String code){
        String query = """
            SELECT p.prd_lvl_number code, prd_full_name name
              from ifhprdmst p 
             where p.prd_lvl_number=?
            """;
        return jdbcTemplate.queryForObject(query,
            new Object[]{code},
            new int[]{Types.VARCHAR},
            new RowMapper<Product>() {
                public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Product product = new Product();
                    product.setCode(rs.getString("code"));
                    product.setName(rs.getString("name"));
                    return product;
                }
            }
        );
    }
    public List<Product> getAll(){
        String query = """
            SELECT 
                p.prd_lvl_number code, 
                prd_full_name name
              from ifhprdmst p 
             where rownum<=100
            """;
        return jdbcTemplate.query(query,
            new RowMapper<Product>() {
                public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Product product = new Product();
                    product.setCode(rs.getString("code"));
                    product.setName(rs.getString("name"));
                    return product;
                }
            }
        );
    }
}

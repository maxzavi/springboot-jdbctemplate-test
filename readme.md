# Java Spring Boot Sample JdbcTemplate and Test in Oracle database using maven

Create Spring Boot project with maven, using dependencies:

- Oracle Driver
- JDBC API

Implements **CommandLineRunner** and add **run** method

```java
public class DemoApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
```

Add logging using **slf4j**

```java
public class DemoApplication implements CommandLineRunner {
    //Logger
	final static Logger LOG = LoggerFactory.getLogger(DemoApplication.class);
```
Settings in **application.properties** file **Exclude from control version for Security**:

```properties
#Logger
logging.level.root=INFO
logging.level.pe=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} - %m%n
```

## Setting Connection Parameters

Use application.properties 
```properties
spring.datasource.url=jdbc:oracle:thin:@{host}:{port}:{sid}
spring.datasource.username={username}
spring.datasource.password={password}
```

Container use environment variables: **SPRING__DATASOURCE__URL** **SPRING__DATASOURCE__USERNAME** **SPRING__DATASOURCE__PASSWORD** 

##JdbcTemplate

Get object using **queryForObject**

```java
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
```

Get lis of objects using **query**
```java
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
```

## Test

Create class ProductRepositoryTest.java, files schema.sql and test-data.sql in resources/jdbc folder

```java
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

```
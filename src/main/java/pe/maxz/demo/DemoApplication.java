package pe.maxz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pe.maxz.demo.model.Product;
import pe.maxz.demo.repository.ProductRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	//Logger
	final static Logger LOG = LoggerFactory.getLogger(DemoApplication.class);
	@Autowired
	ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Start ..");
		var products = productRepository.getAll();
		int counter=0;
		for (var item : products) {
			counter++;
			LOG.info("Product: {}/{}: {}",counter, products.size(), item);
		}
		Product product = productRepository.get("11522");
		LOG.info("Product: {}",product);
	}
}

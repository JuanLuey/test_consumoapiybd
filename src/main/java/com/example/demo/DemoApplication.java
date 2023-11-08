package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Stock;
import com.example.demo.entity.StockInput;
import com.example.demo.repository.RepoApi;
import com.example.demo.repository.RepoData;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	private final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	RepoApi repoApi;

	@Autowired
	RepoData repoData;

	@Override
	public void run(String... args) throws Exception {
		
		String token = repoApi.login();

		List<Stock> stock = repoData.getstock();
		    
	    StockInput stockInput = new StockInput();
        stockInput.setDocumentNumber("123456");
        stockInput.setOrigin("TEST");
        stockInput.setStocks(stock);

		int resultstock = repoApi.stock_inc(stockInput, token);

        log.info("result resultstock code api {} ",resultstock);

		log.info(" final ");
	}

}
package com.agustin.sileoni.TiendaEcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TiendaEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaEcommerceApplication.class, args);
	}

}

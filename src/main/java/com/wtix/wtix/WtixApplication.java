package com.wtix.wtix;

import org.hibernate.dialect.Database;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class WtixApplication {

	public static void main(String[] args) {
		SpringApplication.run(WtixApplication.class, args);
	}

}

package com.example.logincadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class LoginCadastroApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoginCadastroApplication.class, args);
  }

  @Bean
  public DataSource getDataSource() {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
    dataSourceBuilder.url("jdbc:mysql://localhost:3306/formlogcad");
    dataSourceBuilder.username("root");
    dataSourceBuilder.password("root");
    return dataSourceBuilder.build();
  }

}

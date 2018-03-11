package br.com.ivana.evento;

import java.net.URISyntaxException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;




@Configuration
@Profile("prod")
public class DataConfiguration{

	@Bean
    public BasicDataSource dataSource() throws URISyntaxException {
       

        
        String dbUrl = "jdbc:postgresql://" + "ec2-107-22-168-211.compute-1.amazonaws.com" + ':' + "5432"+ "d6pemk2qv7060u";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername("ikrhhgqlyjmxda");
        basicDataSource.setPassword("34574e17fe2320bc5110f109a4db908594deff548179e6c1ef553c9d92a9dbe0");

        return basicDataSource;
    }
}
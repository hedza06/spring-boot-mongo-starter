package com.springbootmongo.starter;

import com.github.mongobee.Mongobee;
import com.github.mongobee.exception.MongobeeException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class StarterApplication {

	public static void main(String[] args)
    {
		SpringApplication.run(StarterApplication.class, args);
		try
        {
            Mongobee runner = new Mongobee("mongodb://localhost:27017/spring_mongo");
            runner.setDbName("spring_mongo");
            runner.setChangeLogsScanPackage(
                    "com.springbootmongo.starter.mongobee"); // package to scan for change sets

            runner.setEnabled(true);
            runner.execute();
        }
        catch (MongobeeException e) {
		    e.printStackTrace();
        }
    }
}

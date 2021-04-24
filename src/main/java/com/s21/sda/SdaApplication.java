package com.s21.sda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.s21.sda.config.dbConfig;
// import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
// import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {
		// MongoAutoConfiguration.class,
		// MongoDataAutoConfiguration.class
})
public class SdaApplication {

	public static void main(String[] args) {
		System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
		SpringApplication.run(SdaApplication.class, args);
		dbConfig dbObj = dbConfig.initiateDB();
		dbConfig dbObj1 = dbConfig.initiateDB();
		System.out.println(dbObj == dbObj1);
	}
}

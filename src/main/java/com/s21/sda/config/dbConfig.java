package com.s21.sda.config;

import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.*;

public class dbConfig {

    private static volatile dbConfig instance;

	public MongoDatabase dbInstance;

    private dbConfig() {
        this.dbInstance = loadDB();
    }

    public static dbConfig initiateDB() {
        dbConfig result = instance;
        if (result != null) {
            return result;
        }
        synchronized(dbConfig.class) {
            if (instance == null) {
                instance = new dbConfig();
            }
            return instance;
        }
    }

	public MongoDatabase loadDB() {
		Map<String, String> env = System.getenv();
		MongoClientURI uri = new MongoClientURI("mongodb+srv://admin:bookwormsda6@cluster0.o2rnn.mongodb.net/bookworm?retryWrites=true&w=majority");
		MongoClient mongoClient = new MongoClient(uri);
		System.out.println("database loaded");
		MongoDatabase database = mongoClient.getDatabase("bookworm");
		return database;
	}
}

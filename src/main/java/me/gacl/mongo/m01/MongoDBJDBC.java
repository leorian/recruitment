package me.gacl.mongo.m01;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by xiezhonggui on 16-5-22.
 */
public class MongoDBJDBC {
    public static void main(String args[]) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

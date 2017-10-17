package com.springbootmongo.starter.mongobee;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

@ChangeLog
public class MongoChangeLog {

    @ChangeSet(order = "001", id = "set_001", author = "hedza06")
    public void roleSeed(MongoDatabase mongoDatabase)
    {
        MongoCollection<Document> roleCollection = mongoDatabase.getCollection("roles");
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("label", "ADMIN");
        roleMap.put("description", "Administration Role");
        roleMap.put("isActive", Boolean.TRUE);

        Document document = new Document(roleMap);
        roleCollection.insertOne(document);
    }

    @ChangeSet(order = "002", id = "set_002", author = "hedza06")
    public void userSeed(MongoDatabase mongoDatabase)
    {
        MongoCollection<Document> userCollection = mongoDatabase.getCollection("users");
        Map<String, Object> userSeedMap = new HashMap<>();
        userSeedMap.put("username", "hedza06");
        userSeedMap.put("password", "hedza123");
        userSeedMap.put("firstName", "Heril");
        userSeedMap.put("lastName", "Muratovic");
        userSeedMap.put("phone", "+38269657962");
        userSeedMap.put("isActive", Boolean.TRUE);

        Document document = new Document(userSeedMap);
        userCollection.insertOne(document);
    }
}

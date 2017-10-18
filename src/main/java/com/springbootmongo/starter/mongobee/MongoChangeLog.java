package com.springbootmongo.starter.mongobee;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ChangeLog
public class MongoChangeLog {

    @ChangeSet(order = "001", id = "set_001", author = "hedza06")
    public void roleSeed(MongoDatabase mongoDatabase)
    {
        MongoCollection<Document> roleCollection = mongoDatabase.getCollection("roles");
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("label", "ROLE_ADMIN");
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
        userSeedMap.put("username", "admin");
        userSeedMap.put("password", new BCryptPasswordEncoder().encode("admin123"));
        userSeedMap.put("firstName", "Heril");
        userSeedMap.put("lastName", "Muratovic");
        userSeedMap.put("phone", "+38269657962");
        userSeedMap.put("isActive", Boolean.TRUE);

        // create role map
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("label", "ROLE_ADMIN");
        roleMap.put("description", "Administration Role");
        roleMap.put("isActive", Boolean.TRUE);

        // create role document
        Document roleDocument = new Document(roleMap);
        List<Document> roles = new ArrayList<>();
        roles.add(roleDocument);
        userSeedMap.put("roles", roles);

        // make user seed.
        Document document = new Document(userSeedMap);
        userCollection.insertOne(document);
    }
}

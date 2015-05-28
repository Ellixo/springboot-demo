package com.ellixo.blog;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestServices {

    @Autowired
    private MongoDbFactory mongo;

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    String ping() {
        String status = "{\"status\":\"OK\",\"timestamp\":\"" + System.currentTimeMillis() + "\"}";

        DBObject dbObject = (DBObject) JSON.parse(status);
        mongo.getDb().getCollection("logs").insert(dbObject);

        return status;
    }

    @RequestMapping(value = "logs", method = RequestMethod.GET)
    public
    @ResponseBody
    String logs() {
        DBCursor cursor = mongo.getDb().getCollection("logs").find();

        StringBuilder sb = new StringBuilder();
        while (cursor.hasNext()) {
            sb.append(cursor.next());
        }

        return sb.toString();
    }

}

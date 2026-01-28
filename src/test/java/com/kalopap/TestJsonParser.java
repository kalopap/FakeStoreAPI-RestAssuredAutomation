package com.kalopap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJsonParser {
    public static void main(String[] args)  {
         testMethod();

    }
    static void testMethod(){
        String aJsonStr =
                "{\"employees\": [{\"id\":121,\"name\":\"emp1\",\"title\": \"developer\", \"age\" : 35,\"weight\" : 123.3, " +
                        "\"courses\" : [\"java\", \"python\",\"CSS\"],\"contact\": {\"address\":\"123 St\", \"phone\": 9876543210 ,\"email\":\"kals@kalopap.com\"}," +
                "\"interests\" : [{\"hobbies\":[\"reading\",\"stock market\"], \"extras\":[\"movies\",\"love stories\"] }] }," +
                "{\"id\":122,\"name\":\"emp2\",\"title\":\"tester\",\"age\":32, \"weight\":145.8," +
                        "\"courses\":[\"automation\",\"cloud testing\"],\"contact\":{\"address\":\"234 State St\",\"phone\":7890654321,\"email\":\"hector@semail.com\"}, " +
                        "\"interests\":[{\"hobbies\":[\"sports\",\"painting\"],\"extras\":[\"movies\",\"thrillers\"] }]  } ]" +
                "}";
        ObjectMapper objMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objMapper.readTree(aJsonStr).get("employees");

            System.out.println(rootNode.get(0).get("title"));


        }catch(JsonProcessingException e){
            e.printStackTrace();
        }

    }
}

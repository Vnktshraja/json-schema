package com.sample.jsonschema.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.google.gson.Gson;
import com.sample.jsonschema.jsonpoc.EmployeeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class JsonValidateController {

    public static final Logger LOGGER = LoggerFactory.getLogger(JsonValidateController.class);


    @PostMapping("/validate")
    public ResponseEntity<String> jsonValidate(@RequestBody EmployeeRequest request) throws IOException, ProcessingException {

        // Load the JSON schema
//        InputStream schemaStream = JsonValidateController.class.getResourceAsStream("/request.schema.json");
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode schemaNode = mapper.readTree(schemaStream);
//        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
//        JsonSchema schema = factory.getJsonSchema(schemaNode);
//
//        // Load the JSON data to validate
//        InputStream dataStream = JsonValidateController.class.getResourceAsStream("/EmployeeRequest.json");
//        JsonNode dataNode = mapper.readTree(request);
//
//        // Perform the validation
//        schema.validate(dataNode);
//        System.out.println("Validation succeeded!");
        JsonNode schemaJson = JsonLoader.fromFile(new File("schema.json"));
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.byDefault();
        JsonSchema schema = schemaFactory.getJsonSchema(schemaJson);

        Gson gson = new Gson();
        String personJson = gson.toJson(request);
        System.out.println(personJson);

//        JsonNode dataJson = JsonLoader.fromString("{\"name\": \"John\"}");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // Convert the Person object to a JSON string
        String jsonString = objectMapper.writeValueAsString(request);
        JsonNode jsonNode = objectMapper.readTree(personJson);
        System.out.println(request);


        ProcessingReport report = schema.validate(jsonNode);
        System.out.println(jsonNode);
        if (report.isSuccess()) {
            return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
        }else
            return new ResponseEntity<>("Invalid Json" +"\n"+"\n",HttpStatus.BAD_REQUEST);

    }

}

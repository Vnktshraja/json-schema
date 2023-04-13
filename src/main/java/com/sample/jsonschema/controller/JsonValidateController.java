package com.sample.jsonschema.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.sample.jsonschema.jsonpoc.EmployeeRequest;
import com.sample.jsonschema.jsonpoc.POCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class JsonValidateController {

    public static final Logger LOGGER = LoggerFactory.getLogger(JsonValidateController.class);

    @Autowired
    private POCService service;


    @PostMapping("/validate")
    public ResponseEntity<String> jsonValidate(@RequestBody String request) throws IOException, ProcessingException {

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

//        JsonNode dataJson = JsonLoader.fromString("{\"name\": \"John\"}");
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(request);
        ProcessingReport report = schema.validate(jsonNode);
 //       System.out.println(jsonNode);
        if (report.isSuccess()) {
            service.saveObject(request);
            return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
        }else
            return new ResponseEntity<>("Invalid Json" +"\n"+"\n"+  report ,HttpStatus.BAD_REQUEST);
    }

}

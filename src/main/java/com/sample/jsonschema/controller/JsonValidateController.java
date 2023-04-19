package com.sample.jsonschema.controller;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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


        JsonNode schemaJson = JsonLoader.fromFile(new File("schema.json"));
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.byDefault();
        JsonSchema schema = schemaFactory.getJsonSchema(schemaJson);
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(SerializationFeature.INDENT_OUTPUT);

//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String personJson = gson.toJson(request);
//        System.out.println(personJson);

        // Convert the Person object to a JSON string
        String jsonString = mapper.writeValueAsString(request);
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(request);


        ProcessingReport report = schema.validate(jsonNode);
        System.out.println(jsonNode);
        if (report.isSuccess()) {
            return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
        }else
            return new ResponseEntity<>("Invalid Json" +"\n"+"\n",HttpStatus.BAD_REQUEST);

    }

}

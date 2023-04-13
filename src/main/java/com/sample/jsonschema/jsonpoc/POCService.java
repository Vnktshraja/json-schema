package com.sample.jsonschema.jsonpoc;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class POCService {
    private final Logger LOGGER = LoggerFactory.getLogger(POCService.class);

    public void saveObject(String request){
        List<String> employeeRequestList = new ArrayList<>();
        employeeRequestList.add(request);
        LOGGER.info("json schema : " + employeeRequestList);
    }

}

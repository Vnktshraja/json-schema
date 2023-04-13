package com.sample.jsonschema.jsonpoc;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequest {
    private Employee employee;
    private Project project;
}

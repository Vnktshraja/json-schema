package com.sample.jsonschema.jsonpoc;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeRequest {
    private Employee employee;
    private Project project;
}

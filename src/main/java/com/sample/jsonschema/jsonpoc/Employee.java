package com.sample.jsonschema.jsonpoc;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Employee {
    private Integer empId;
    private String name;
    private Address address;
    private List<String> phoneNumber;
}

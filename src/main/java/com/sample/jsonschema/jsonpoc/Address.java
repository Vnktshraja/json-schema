package com.sample.jsonschema.jsonpoc;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Address {
    private Integer doorNo;
    private String streetName;
    private String city;
}

package com.sample.jsonschema.jsonpoc;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    private Integer doorNo;
    private String streetName;
    private String city;
}

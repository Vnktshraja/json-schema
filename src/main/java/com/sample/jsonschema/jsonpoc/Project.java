package com.sample.jsonschema.jsonpoc;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Project {
    private String projectName;
    private String location;
    private Boolean initiated;
}

package com.sample.jsonschema.jsonpoc;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {
    private String projectName;
    private String location;
    private Boolean initiated;
}

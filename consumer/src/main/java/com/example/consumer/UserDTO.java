package com.example.consumer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO implements Serializable {

    private String name;
    private String role;
    private int age;
}

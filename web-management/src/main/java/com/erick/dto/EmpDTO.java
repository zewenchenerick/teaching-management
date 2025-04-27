package com.erick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Encapsulate login Information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {

    private Integer id;
    private String username;
    private String name;
    private String token;

}

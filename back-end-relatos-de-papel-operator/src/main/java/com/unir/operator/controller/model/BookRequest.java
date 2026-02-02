package com.unir.operator.controller.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    @NotNull(message = "`books` cannot be null")
    @NotEmpty(message = "`books` cannot be empty")
    private String ID;
    @NotNull(message = "`books` cannot be null")
    @NotEmpty(message = "`books` cannot be empty")
    private Integer units;

}

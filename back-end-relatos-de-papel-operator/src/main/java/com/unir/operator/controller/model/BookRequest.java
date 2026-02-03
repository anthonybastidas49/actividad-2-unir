package com.unir.operator.controller.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    @NotNull(message = "`id` cannot be null")
    @NotEmpty(message = "`id` cannot be empty")
    private String ID;
    @NotNull(message = "`quantity` cannot be null")
    @Positive(message = "`quantity` cannot be empty")
    private Integer quantity;

}

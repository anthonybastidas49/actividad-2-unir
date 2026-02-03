package com.unir.operator.controller.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperatorRequest {
    @NotNull(message = "`books` cannot be null")
    @NotEmpty(message = "`books` cannot be empty")
    private List<BookRequest> books;
    @NotNull(message = "`client id` cannot be null")
    @NotEmpty(message = "`client id` cannot be empty")
    private String clientID;
    @NotNull(message = "`totalAmount` cannot be null")
    @Positive(message = "`totalAmount` must be greater than 0")
    private Float totalAmount;
}

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
public class OperatorRequest {
    @NotNull(message = "`books` cannot be null")
    @NotEmpty(message = "`books` cannot be empty")
    private List<BookRequest> books;
    @NotNull(message = "`books` cannot be null")
    @NotEmpty(message = "`books` cannot be empty")
    private String clientID;
    @NotNull(message = "`books` cannot be null")
    @NotEmpty(message = "`books` cannot be empty")
    private Long totalAmount;
}

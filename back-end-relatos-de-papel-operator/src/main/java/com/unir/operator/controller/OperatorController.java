package com.unir.operator.controller;

import com.unir.operator.controller.model.OperatorRequest;
import com.unir.operator.data.model.Order;
import com.unir.operator.service.OperatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Operator Controller", description = "Microservicio encargado de exponer operaciones para la gestion de operaciones alojadas en una base de datos en memoria.")
public class OperatorController {

    private final OperatorService service;

    @Operation(
            operationId = "Crear Ordenes",
            description = "Operacion de escritura para crear una nueva orden en la base de datos.",
            summary = "Se recibe una orden y se almacena en la base de datos. Se devuelve la orden creada.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el book con el identificador indicado.")
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Ha ocurrido un error inesperado.")
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OperatorRequest request) {

        log.info("Creating order...");
        Order created = service.createOrder(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(
            operationId = "Obtener Todas las Ordenes",
            description = "Operacion de lectura para obtener todas las ordenes almacenadas en la base de datos.",
            summary = "Se devuelve una lista con todas las ordenes existentes."
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))
    )
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Ha ocurrido un error inesperado."
    )
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {

        List<Order> orders = service.getOrders();
        if (orders != null) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
    @Operation(
            operationId = "Obtener Orden por ID",
            description = "Operacion de lectura para obtener una orden especifica por su identificador.",
            summary = "Se recibe un ID y se devuelve la orden correspondiente si existe."
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))
    )
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la orden con el identificador indicado."
    )
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Ha ocurrido un error inesperado."
    )
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@Parameter(name = "id", description = "Identificador de la orden", required = true) @PathVariable String id) {

        Order order = service.getOrder(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

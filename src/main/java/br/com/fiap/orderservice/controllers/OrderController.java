package br.com.fiap.orderservice.controllers;

import br.com.fiap.orderservice.bean.Order;
import br.com.fiap.orderservice.exceptions.OrderNotFoundException;
import br.com.fiap.orderservice.exceptions.UpdateOrderException;
import br.com.fiap.orderservice.services.OrderService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@Slf4j
@RestController
@Api(value = "Order", description = "Order Service REST API")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @ApiOperation(httpMethod = "GET", value = "Get Order By Id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns Order and Success Status", response = Order.class)
    })
    @GetMapping("/findById/{id}")
    public ResponseEntity<Order> find(@ApiParam( value = "Order Id", required = true) @PathVariable("id") int id){
        Order order = this.service.findById(id);
        if (order == null) {
            throw new OrderNotFoundException("Pedido não encontrado");
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "POST", value = "Post Order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns generated Order and Created Status", response = Order.class)
    })
    @PostMapping("/save")
    public ResponseEntity<Object> saveOrder(@RequestBody Order order){
        Order savedOrder = this.service.save(order);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/findById/{id}")
                .buildAndExpand(savedOrder.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(httpMethod = "PATCH", value = "Update Order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success Status")
    })
    @PatchMapping("/update")
    public ResponseEntity updateOrder(@RequestBody Order order){
        if (!this.service.update(order))
            throw new UpdateOrderException("Erro ao atualizar pedido.");

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "DELETE", value = "Delete Order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retuns Success Status")
    })
    @DeleteMapping("/order/{id}")
    public ResponseEntity deleteOrder(@ApiParam( value = "Order Id", required = true) @PathVariable("id") int id){
        if (this.service.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
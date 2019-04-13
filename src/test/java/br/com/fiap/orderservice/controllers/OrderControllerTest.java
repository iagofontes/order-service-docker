package br.com.fiap.orderservice.controllers;

import br.com.fiap.orderservice.bean.Order;
import br.com.fiap.orderservice.repositories.OrderRepository;
import br.com.fiap.orderservice.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService service;

    @MockBean
    private OrderRepository repo;

    @Test
    public void notFoundOrder() throws Exception {
        int id = 1;
        mvc.perform(get("/findById/"+id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andExpect(content().string(containsString("Pedido não encontrado")));
    }

    @Test
    public void shouldReturnOrder() throws Exception {
        int orderId = 1;
        Order order = new Order(orderId);
        when(this.service.findById(orderId)).thenReturn(order);

        mvc.perform(get("/findById/" + orderId)
            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
            .andExpect(response -> {
                String json = response.getResponse().getContentAsString();
                Order orderFound = new ObjectMapper().readValue(json, Order.class);
                assertThat(orderFound).isEqualToComparingFieldByField(order);
            });
    }

    @Test
    public void shouldReturn200WhenDeleteOrderIsSuccessfull() throws Exception {
        when(this.service.delete(anyInt())).thenReturn(true);

        mvc.perform(delete("/order/" + anyInt())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestWhenOrderIsNotDeletedOrFound() throws Exception {
        when(this.service.delete(anyInt())).thenReturn(false);

        mvc.perform(delete("/order/" + anyInt())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturnAddedOrder() throws Exception {
        Order order = new Order();
        order.setNomeCompleto("Juliano Marques Nunes");
        when(this.service.save(order)).thenReturn(order);

        String orderString = new ObjectMapper().writeValueAsString(order);
        mvc.perform(post("/save").contentType(MediaType.APPLICATION_JSON).content(orderString)
            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
}
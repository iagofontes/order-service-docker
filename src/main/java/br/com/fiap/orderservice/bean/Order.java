package br.com.fiap.orderservice.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Order {
    @ApiModelProperty(notes = "The database generated Order ID")
    private int id;

    @ApiModelProperty( notes = "User E-mail")
    private String email;

    @ApiModelProperty( notes = "User Name")
    private String nomeCompleto;

    @ApiModelProperty( notes = "User Shipping Address")
    private String shippingAddress;

    @ApiModelProperty( notes = "List Order Items")
    private List<Item> items;

    @ApiModelProperty( notes = "Total Amount")
    private BigDecimal precoTotal;

    @ApiModelProperty( notes = "Payment Gateway")
    private Payment formaPagto;

    @ApiModelProperty( notes = "Order Date")
    private String data;

    @ApiModelProperty( notes = "Order Status")
    private String status;
    public Order(int id) { this.id = id; }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Order))
            return false;
        if (obj == this)
            return true;

        Order order= (Order) obj;
        return order.getId() == this.id;
    }
}

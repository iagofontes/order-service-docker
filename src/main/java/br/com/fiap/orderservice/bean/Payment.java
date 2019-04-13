package br.com.fiap.orderservice.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @ApiModelProperty(notes = "The database generated Order ID")
    private int id;

    @ApiModelProperty(notes = "Credit card Number")
    private String numeroCartao;

    @ApiModelProperty(notes = "Expiration Date")
    private String validade;

    @ApiModelProperty(notes = "Credit Card Flag")
    private String bandeira;
}

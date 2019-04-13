package br.com.fiap.orderservice.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @ApiModelProperty(notes = "Item Description")
    private String descricaoItem;

    @ApiModelProperty(notes = "Item Price")
    private BigDecimal precoItem;

    @ApiModelProperty(notes = "Item Quantity")
    private BigDecimal quantidadeItem  = BigDecimal.ZERO;
}

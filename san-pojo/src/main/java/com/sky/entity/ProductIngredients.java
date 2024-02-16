package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜品配料
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductIngredients implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    //商品id
    private Long productId;

    //配料数据list
    private String value;

}

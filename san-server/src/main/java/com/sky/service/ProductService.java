package com.sky.service;

import com.sky.dto.ProductPageQueryDTO;
import com.sky.entity.Product;
import com.sky.result.PageResult;
import com.sky.vo.ProductVO;

import java.util.List;


public interface ProductService {


    /**
     * 条件查询菜品
     *
     * @param
     * @return
     */
    List<ProductVO> listWithCategory(Product product);


    /**
     * 根据产品名称或配料或功能搜索
     *
     * @param productName
     * @return
     */
    List<Product> getByName(String productName);
}

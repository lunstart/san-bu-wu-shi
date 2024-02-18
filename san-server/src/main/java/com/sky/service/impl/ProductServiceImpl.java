package com.sky.service.impl;

import com.sky.entity.Product;
import com.sky.mapper.ProductMapper;
import com.sky.service.ProductService;
import com.sky.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductMapper productMapper;


    /**
     * 条件查询菜品和口味
     *
     * @param product
     * @return
     */
    public List<ProductVO> listWithCategory(Product product) {
        List<Product> productList = productMapper.list(product);

        List<ProductVO> productVOList = new ArrayList<>();

        for (Product d : productList) {
            System.out.println(d);
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(d, productVO);
            productVOList.add(productVO);
        }
        return productVOList;
    }


    /**
     * 根据产品名称或配料或功能搜索
     *
     * @param productName
     * @return
     */
    public List<Product> getByName(String productName) {
        List<Product> products = productMapper.search(productName);
        return products;
    }
}


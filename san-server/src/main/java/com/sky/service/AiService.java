package com.sky.service;

import com.sky.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AiService {
    /**
     * AI推荐
     *
     * @param str
     * @return
     */
    List<Product> list(String str);

    /**
     * 热门推荐
     *
     * @return
     */
    List<Product> getproduct();
}


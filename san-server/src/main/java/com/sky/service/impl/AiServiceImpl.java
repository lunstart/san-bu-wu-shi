package com.sky.service.impl;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.BasicTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.sky.entity.Product;
import com.sky.mapper.ProductMapper;
import com.sky.service.AiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AiServiceImpl implements AiService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * AI推荐
     *
     * @param str
     * @return
     */

    public List<Product> list(String str) {
        //关键词提取
        List<String> keywordList = new ArrayList();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            String key = String.valueOf(c);
            keywordList.add(key);
        }
        List<Product> productList = new ArrayList();
        for (String keyword : keywordList) {
            productList.addAll(productMapper.search(keyword));
        }
        return productList;
    }


    /**
     * 热门推荐
     *
     * @return
     */
    public List<Product> getproduct() {
        List<Product> productList = new ArrayList();
        productList.add(productMapper.getById(1L));
        productList.add(productMapper.getById(2L));
        productList.add(productMapper.getById(3L));
        return productList;
    }

}

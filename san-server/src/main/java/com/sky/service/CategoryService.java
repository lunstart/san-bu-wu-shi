package com.sky.service;


import com.sky.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}

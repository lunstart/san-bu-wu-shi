package com.sky.service;

import com.sky.dto.ProductDTO;
import com.sky.entity.Product;
import com.sky.vo.ProductVO;

import java.util.List;


public interface ProductService {

    /**
     * 新增商品
     *
     * @param productDTO
     */
    public void save(ProductDTO productDTO);


    /**
     * 条件查询商品
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

    /**
     *商品批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询商品
     *
     * @param id
     * @return
     */
    ProductVO getById(Long id);

    /**
     * 根据id修改菜品基本信息
     * 
     * @param productDTO
     */
    void update(ProductDTO productDTO);

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    List<Product> list(Long categoryId);
}

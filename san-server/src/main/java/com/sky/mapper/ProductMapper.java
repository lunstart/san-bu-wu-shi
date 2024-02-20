package com.sky.mapper;

import com.sky.entity.Product;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ProductMapper {

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    Product getById(Long id);


    /**
     * 动态条件查询商品
     *
     * @param product
     * @return
     */
    List<Product> list(Product product);

    /**
     * 根据id动态修改菜品数据
     *
     * @param product
     */

    void update(Product product);

    /**
     * 动态搜索查询商品
     *
     * @param productName
     * @return
     */
    List<Product> search(String productName);


    /**
     * 插入菜品数据
     *
     * @param product
     */
    void insert(Product product);

    /**
     * 根据主键删除菜品数据
     *
     * @param id
     */
    @Delete("delete from product where id = #{id}")
    void deleteById(Long id);

}

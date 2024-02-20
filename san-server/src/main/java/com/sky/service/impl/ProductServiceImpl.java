package com.sky.service.impl;

import com.sky.constant.StatusConstant;
import com.sky.dto.ProductDTO;
import com.sky.entity.Product;
import com.sky.mapper.ProductMapper;
import com.sky.service.ProductService;
import com.sky.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 新增商品
     *
     * @param productDTO
     */
    @Transactional
    public void save(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);

        //向菜品表插入1条数据
        productMapper.insert(product);

    }

    /**
     * 菜品批量删除
     *
     * @param ids
     */
    @Transactional
    public void deleteBatch(List<Long> ids) {
//        //判断当前菜品是否能够删除---是否存在起售中的菜品？？
//        for (Long id : ids) {
//            Product product = productMapper.getById(id);
//            if (product.getStatus() == StatusConstant.ENABLE) {
//                //当前菜品处于起售中，不能删除
//                throw new DeletionNotAllowedException(MessageConstant.PRODUCT_ON_SALE);
//            }
//        }

        //删除菜品表中的菜品数据
        for (Long id : ids) {
            productMapper.deleteById(id);
        }
    }

    /**
     * 根据id查询菜品和对应的口味数据
     *
     * @param id
     * @return
     */
    public ProductVO getById(Long id) {
        //根据id查询菜品数据
        Product product = productMapper.getById(id);

        //将查询到的数据封装到VO
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(product, productVO);

        return productVO;
    }

    /**
     * 根据id修改菜品基本信息和对应的口味信息
     *
     * @param productDTO
     */
    public void update(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        //修改菜品表基本信息
        productMapper.update(product);
    }

    /**
     * 根据分类id查询商品
     *
     * @param categoryId
     * @return
     */
    public List<Product> list(Long categoryId) {
        Product product = Product.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();
        return productMapper.list(product);
    }

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


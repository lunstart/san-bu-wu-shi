package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Product;
import com.sky.result.Result;
import com.sky.service.ProductService;
import com.sky.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("userProductController")
@RequestMapping("/user/product")
@Slf4j
@Api(tags = "C端-菜品浏览接口")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<ProductVO>> list(Long categoryId) {

//        //构造redis中的key，规则：product_分类id
//        String key = "product_" + categoryId;
//
//        //查询redis中是否存在菜品数据
//        List<ProductVO> list = (List<ProductVO>) redisTemplate.opsForValue().get(key);
//        if(list != null && list.size() > 0){
//            //如果存在，直接返回，无须查询数据库
//            return Result.success(list);
//        }

        Product product = new Product();
        product.setCategoryId(categoryId);
        product.setStatus(StatusConstant.ENABLE);//查询起售中的菜品

        //如果不存在，查询数据库，将查询到的数据放入redis中
        List<ProductVO> list = productService.listWithCategory(product);
//        redisTemplate.opsForValue().set(key, list);

        return Result.success(list);
    }

    @GetMapping("/getByName")
    @ApiOperation("根据产品名称或配料或功能搜索")
    public Result<List<Product>> list(String productName) {
        List<Product> products = productService.getByName(productName);
        return Result.success(products);
    }
}

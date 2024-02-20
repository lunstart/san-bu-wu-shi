package com.sky.controller.admin;

import com.sky.dto.ProductDTO;
import com.sky.entity.Product;
import com.sky.result.Result;
import com.sky.service.ProductService;
import com.sky.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


/**
 * 商品管理
 */
@RestController
@RequestMapping("/admin/product")
@Api(tags = "商家商品相关接口")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增商品
     *
     * @param productDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增商品")
    public Result save(@RequestBody ProductDTO productDTO) {
        log.info("新增商品：{}", productDTO);
        productService.save(productDTO);

        //清理缓存数据
//        String key = "product_" + productDTO.getCategoryId();
//        cleanCache(key);
        return Result.success();
    }


    /**
     * 商品批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("商品批量删除")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("商品批量删除：{}", ids);
        productService.deleteBatch(ids);

        //将所有的商品缓存数据清理掉，所有以product_开头的key
        //cleanCache("product_*");

        return Result.success();
    }

    /**
     * 根据id查询商品
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询商品")
    public Result<ProductVO> getById(@PathVariable Long id) {
        log.info("根据id查询商品：{}", id);
        ProductVO productVO = productService.getById(id);
        return Result.success(productVO);
    }

    /**
     * 修改商品
     *
     * @param productDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改商品")
    public Result update(@RequestBody ProductDTO productDTO) {
        log.info("修改商品：{}", productDTO);
        productService.update(productDTO);

        //将所有的商品缓存数据清理掉，所有以product_开头的key
        //cleanCache("product_*");
        return Result.success();
    }

    /**
     * 根据分类id查询商品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询商品")
    public Result<List<Product>> list(Long categoryId) {
        List<Product> list = productService.list(categoryId);
        return Result.success(list);
    }

    /**
     * 清理缓存数据
     *
     * @param pattern
     */
    private void cleanCache(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}

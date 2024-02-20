package com.sky.controller.user;

import com.sky.entity.Product;
import com.sky.result.Result;
import com.sky.service.AiService;
import com.sky.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "C端AI对话接口")
@RestController
@RequestMapping("/user/ai")
public class AIController {
    @Autowired
    private AiService aiService;

    @PostMapping
    @ApiOperation("ai推荐")
    public Result<List<Product>> getResult(String str) {
        List<Product> products = aiService.list(str);
        if(products == null || products.size() == 0){
            return Result.error("没有找到适合的商品，看一下热门推荐吧！");
        }
        return Result.success(products);
    }

    @GetMapping("/product")
    @ApiOperation("热门推荐")
    public Result<List<Product>> list() {
        List<Product> products = aiService.getproduct();
        return Result.success(products);
    }
}

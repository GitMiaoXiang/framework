package com.mx.framework.controller;

import com.mx.framework.cosntenum.ResponseEnum;
import com.mx.framework.entity.cto.ResultData;
import com.mx.framework.entity.vo.CategoryVo;
import com.mx.framework.mapper.CategoryMapper;
import com.mx.framework.service.ICategoryService;
import com.mx.framework.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categoryList")
    public ResultData<List<CategoryVo>> categoryList(){
        ResultData<List<CategoryVo>> listResultData = categoryService.categoryList();
        return listResultData;
    }
}

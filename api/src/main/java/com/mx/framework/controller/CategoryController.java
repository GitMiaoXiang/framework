package com.mx.framework.controller;

import com.mx.framework.dto.ResultData;
import com.mx.framework.vo.CategoryVo;
import com.mx.framework.service.ICategoryService;
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

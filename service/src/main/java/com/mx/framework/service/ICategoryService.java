package com.mx.framework.service;

import com.mx.framework.base.service.IBaseService;
import com.mx.framework.po.Category;
import com.mx.framework.dto.ResultData;
import com.mx.framework.vo.CategoryVo;

import java.util.List;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2018/12/6 16:21
 * Modified By :
 */
public interface ICategoryService extends IBaseService<Category> {

    /**
     * 查询首页分类
     * @return
     */
    ResultData<List<CategoryVo>> categoryList();
}

package com.mx.framework.service;

import com.mx.framework.base.service.IBaseService;
import com.mx.framework.entity.model.Category;
import com.mx.framework.entity.cto.ResultData;
import com.mx.framework.entity.vo.CategoryVo;

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

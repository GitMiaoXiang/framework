package com.mx.framework.service.impl;

import com.mx.framework.base.service.BaseService;
import com.mx.framework.cosntenum.ResponseEnum;
import com.mx.framework.entity.model.Category;
import com.mx.framework.entity.cto.ResultData;
import com.mx.framework.entity.vo.CategoryVo;
import com.mx.framework.mapper.CategoryMapper;
import com.mx.framework.service.ICategoryService;
import com.mx.framework.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2018/12/6 16:20
 * Modified By :
 */
@Service
public class CategoryServiceImpl extends BaseService<Category> implements ICategoryService {
    
    @Autowired
    private CategoryMapper categoryMapper;

    public ResultData<List<CategoryVo>> categoryList(){
        List<Category> categories = categoryMapper.selectAll();
        List<CategoryVo> categoryVos = new LinkedList<>();
        for (Category category:categories) {
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setDescribe(category.getDescribe());
            categoryVo.setId(category.getId());
            categoryVo.setLink(category.getLink());
            categoryVo.setName(category.getName());
            categoryVo.setRank(category.getRank());
            categoryVo.setRootId(category.getRootId());
            List<Category> categoriesNew = new LinkedList<>();
            for (Category categoryTwo:categories){
                if(category.getId().equals(categoryTwo.getRootId())){
                    categoriesNew.add(categoryTwo);
                }
                if(!categoriesNew.isEmpty()){
                    categoryVo.setCategoryDetails(categoriesNew);
                }
            }
            categoryVos.add(categoryVo);
        }
        ResultData<List<CategoryVo>> listResultData = ResultUtil.successResult(categoryVos, ResponseEnum.SUCCESS);
        return listResultData;
    }
}

package com.mx.framework.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mx.framework.po.Category;
import lombok.Data;

import java.util.List;

/**
 * @author ShangGuanMingPeng
 * date: 2018/12/6 16:00
 * Description: 分类传输模型
 * Modified By :
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryVo extends Category {

    private List<Category> categoryDetails;
}

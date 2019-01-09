package com.mx.framework.service;

import com.mx.framework.base.service.BaseService;
import com.mx.framework.base.service.IBaseService;
import com.mx.framework.entity.cto.ResultData;
import com.mx.framework.entity.model.Article;

import java.util.List;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2018/12/6 19:58
 * Modified By :
 */
public interface IArticleService extends IBaseService<Article> {

    /**
     * 首页文章数据
     * @param pageIndex
     * @return
     */
    ResultData<List<Article>> articleList(int pageIndex);
}

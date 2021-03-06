package com.mx.framework.service;

import com.mx.framework.base.service.IBaseService;
import com.mx.framework.dto.ResultData;
import com.mx.framework.po.Article;

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

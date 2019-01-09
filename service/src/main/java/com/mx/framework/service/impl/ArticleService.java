package com.mx.framework.service.impl;

import com.github.pagehelper.PageInfo;
import com.mx.framework.base.service.BaseService;
import com.mx.framework.cosntenum.ResponseEnum;
import com.mx.framework.entity.Const;
import com.mx.framework.entity.cto.ResultData;
import com.mx.framework.entity.model.Article;
import com.mx.framework.service.IArticleService;
import com.mx.framework.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2018/12/6 20:00
 * Modified By :
 */
@Service
public class ArticleService extends BaseService<Article> implements IArticleService {

    @Resource
    private Const aConst;

    @Override
    public ResultData<List<Article>> articleList(int pageIndex) {
        PageInfo<Article> articlePageInfo = queryPageListByWhere(pageIndex,Integer.valueOf(aConst.getPageSize()), null);
        List<Article> articles = articlePageInfo.getList();
        ResultData<List<Article>> listResultData = ResultUtil.successResult(articles, ResponseEnum.SUCCESS);
        return listResultData;
    }

}

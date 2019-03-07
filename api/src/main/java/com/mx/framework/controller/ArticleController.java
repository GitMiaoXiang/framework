package com.mx.framework.controller;

import com.mx.framework.cosntenum.ResponseEnum;
import com.mx.framework.dto.ResultData;
import com.mx.framework.po.Article;
import com.mx.framework.service.IArticleService;
import com.mx.framework.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : ShangGuanMingPeng
 * Description : 文章Controller
 * Date :Create in 2018/12/6 19:56
 * Modified By :
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    /**
     * 文章首页内容
     * @param pageIndex
     * @return
     */
    @GetMapping("/list")
    public ResultData<List<Article>> articleList(@RequestParam(value = "pageIndex", defaultValue = "1",required = false) Integer pageIndex){
        ResultData<List<Article>> listResultData = articleService.articleList(pageIndex);
        return listResultData;
    }

    /**
     * 保存文章
     * @param article
     * @return
     */
    @PostMapping("/save")
    @CacheEvict
    public ResultData add(Article article){
        Integer save = articleService.save(article);
        ResultData resultData;
        if(save.equals(1)){
            resultData = ResultUtil.successResult(ResponseEnum.SUCCESS);
        }else{
            resultData = ResultUtil.errorResult(ResponseEnum.SAVE_FAILED);
        }
        return resultData;
    }

    /**
     * 审核文
     * @param id
     * @return
     */
    @PutMapping("/updateAudit")
    public ResultData audit(Integer id){
        ResultData resultData;
        Article article = new Article();
        article.setId(id);
        article.setIsAudit("1");
        Integer isSuccess = articleService.updateSelective(article);
        if(isSuccess.equals(1)){
            resultData = ResultUtil.successResult(ResponseEnum.SUCCESS);
        }else{
            resultData = ResultUtil.errorResult(ResponseEnum.SAVE_FAILED);
        }
        return resultData;
    }



}

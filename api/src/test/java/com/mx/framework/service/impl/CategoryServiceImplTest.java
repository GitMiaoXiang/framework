package com.mx.framework.service.impl;

import com.mx.framework.ApiApplication;
import com.mx.framework.dto.ResultData;
import com.mx.framework.po.Article;
import com.mx.framework.vo.CategoryVo;
import com.mx.framework.mapper.ArticleMapper;
import com.mx.framework.service.IArticleService;
import com.mx.framework.service.ICategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2018/12/6 17:22
 * Modified By :
 */
@SpringBootTest(classes = ApiApplication.class)
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void categoryList() {
        ResultData<List<CategoryVo>> listResultData = categoryService.categoryList();
        System.out.println(listResultData.toString());
    }

    @Test
    public void add(){
        long startTime = System.currentTimeMillis();
        List<Article> list = new LinkedList<>();
        for (int i = 0;i<100;i++){
            Article article = new Article();
            article.setTitle("Spring Boot 创建全局变量");
            article.setArDesc("Spring Boot 创建全局变量");
            article.setCategory(2);
            article.setClickCount(0);
            article.setArContent("@ConfigurationProperties(prefix = \"ziweidajiu\")\n" +
                    "public class Ziweidajiu {\n" +
                    "\n" +
                    "    private String redisPath;\n" +
                    "\n" +
                    "    public String getRedisPath() {\n" +
                    "        return redisPath;\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setRedisPath(String redisPath) {\n" +
                    "        this.redisPath = redisPath;\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "作者：紫薇大舅\n" +
                    "链接：https://www.jianshu.com/p/0edda665e87b\n" +
                    "來源：简书\n" +
                    "简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。");
            article.setCreateDate(new Date());
            article.setIsAudit("0");
            article.setIsTop("1");
            article.setTags("SpringBoot");
            article.setUpdateDate(new Date());
            list.add(article);
        }
        articleMapper.insertList(list);
        long endTime = System.currentTimeMillis();
        long time = endTime-startTime;
        System.out.println(time/1000);
    }
}
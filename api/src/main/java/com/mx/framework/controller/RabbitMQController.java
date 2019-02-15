package com.mx.framework.controller;

import com.mx.framework.po.Article;
import com.mx.framework.rabbitmq.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2019/1/10 22:26
 * Modified By :
 */
@RestController
public class RabbitMQController {

    @Autowired
    private RabbitSender rabbitSender;

    @GetMapping("/sendArticle")
    public void sendArticle(){
        Article article = new Article();
        article.setArContent("haha");
        rabbitSender.sendArticle(article);
    }

}

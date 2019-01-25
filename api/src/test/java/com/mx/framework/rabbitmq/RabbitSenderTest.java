package com.mx.framework.rabbitmq;

import com.mx.framework.ApiApplication;
import com.mx.framework.entity.model.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in ${date} ${time}
 * Modified By :
 */
@SpringBootTest(classes = ApiApplication.class)
@RunWith(SpringRunner.class)
public class RabbitSenderTest {

    @Autowired
    private RabbitSender rabbitSender;

    @Test
    public void send() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("number", "123456");
        hashMap.put("send_time", new Date().toString());
        rabbitSender.send("Hello SpringBoot RabbitMq", hashMap);
    }

    @Test
    public void sendArticle() {
        Article article = new Article();
        article.setArContent("haha");
        rabbitSender.sendArticle(article);
    }
}
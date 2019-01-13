package com.mx.framework.controller;

import com.mx.framework.cosntenum.ResponseEnum;
import com.mx.framework.entity.cto.ResultData;
import com.mx.framework.rabbitmq.MsgProducer;
import com.mx.framework.utils.ResultUtil;
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
    private MsgProducer msgProducer;

    @GetMapping("/sendMsg")
    public ResultData<String> sendMsg(){
        msgProducer.sendMsg("Hello Work");
        return ResultUtil.successResult(ResponseEnum.SUCCESS);
    }
}

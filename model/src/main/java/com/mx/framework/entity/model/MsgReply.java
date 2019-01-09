package com.mx.framework.entity.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "msg_reply")
@Data
public class MsgReply {

    @Id
    private Integer id;

    private Integer msgId;

    private Integer replyId;

    private Date time;

    private String content;

    private Integer userId;

}
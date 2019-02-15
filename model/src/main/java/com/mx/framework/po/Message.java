package com.mx.framework.po;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "message")
@Data
public class Message {
    @Id
    private Integer id;

    private Integer userId;

    private Date time;

    private String status;

    private String content;

    private Integer arId;

}
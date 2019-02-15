package com.mx.framework.po;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "article")
@Data
public class Article implements Serializable {
    @Id
    private Integer id;

    private String title;

    private String isTop;

    private Integer clickCount;

    private Date createDate;

    private Date updateDate;

    private Integer category;

    private String tags;

    private String isAudit;

    private String arDesc;

    private String arContent;
}
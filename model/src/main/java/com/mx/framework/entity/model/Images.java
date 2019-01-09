package com.mx.framework.entity.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "images")
@Data
public class Images {

    @Id
    private Integer id;

    private String name;

    private String title;

    private Date uploadTime;

    private Integer arId;

    private int type;

    private int isAudit;

    private int isTop;

    private String describe;

    private String path;

}
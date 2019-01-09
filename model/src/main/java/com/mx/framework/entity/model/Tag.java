package com.mx.framework.entity.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tag")
@Data
public class Tag {
    @Id
    private Integer id;

    private String name;

    private Date time;

    private Integer number;

}
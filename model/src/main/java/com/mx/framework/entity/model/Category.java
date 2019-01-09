package com.mx.framework.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "category")
@Data
public class Category implements Cloneable{
    @Id
    private Integer id;

    private String name;

    @Column(name = "rankz")
    private String rank;

    private String link;

    @Column(name = "descz")
    private String describe;

    private Integer rootId;

    @Override
    public Object clone() {
        Category category = null;
        try {
            category = (Category) category.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }
}
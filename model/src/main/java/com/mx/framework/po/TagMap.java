package com.mx.framework.po;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tag_map")
@Data
public class TagMap {
    @Id
    private Integer id;

    private Integer arId;

    private Integer taId;

}
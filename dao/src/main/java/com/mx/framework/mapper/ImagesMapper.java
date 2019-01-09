package com.mx.framework.mapper;

import com.mx.framework.base.mapper.MyMapper;
import com.mx.framework.entity.model.Images;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author : ShangGuanMingPeng
 * Description : 
 * Date :Create in   
 * Modified By :
 */
@Mapper
@Repository
public interface ImagesMapper extends MyMapper<Images> {
}

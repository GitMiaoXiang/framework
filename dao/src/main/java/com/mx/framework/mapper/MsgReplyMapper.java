package com.mx.framework.mapper;

import com.mx.framework.base.mapper.MyMapper;
import com.mx.framework.po.MsgReply;
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
public interface MsgReplyMapper extends MyMapper<MsgReply> {
}

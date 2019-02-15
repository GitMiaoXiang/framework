package com.mx.framework.base.service;

import com.mx.framework.dto.ResultData;

import java.util.Map;

/**
 * @Author:上官名鹏
 * @Description:
 * @Date:Create in 2018/11/3 9:51
 * Modified By:
 */
public interface IHttpApiService {

    ResultData doGet(String url);

    ResultData doGet(String url, Map<String, Object> map);

    ResultData doPost(String url, Map<String, Object> map);

    ResultData doPost(String url);
}

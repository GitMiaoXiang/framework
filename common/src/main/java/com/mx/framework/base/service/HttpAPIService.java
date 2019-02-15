package com.mx.framework.base.service;

import com.mx.framework.utils.ResultUtil;
import com.mx.framework.cosntenum.ResponseEnum;
import com.mx.framework.dto.HttpResult;
import com.mx.framework.dto.ResultData;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HttpAPIService implements IHttpApiService {

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    @Override
    public ResultData doGet(String url){
        try{
            // 声明 http get 请求
            HttpGet httpGet = new HttpGet(url);

            // 装载配置信息
            httpGet.setConfig(config);

            // 发起请求
            CloseableHttpResponse response = this.httpClient.execute(httpGet);

            // 判断状态码是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 返回响应体的内容
                return ResultUtil.successResult(EntityUtils.toString(response.getEntity(), "UTF-8"),ResponseEnum.SUCCESS);
            }else{
                return ResultUtil.errorResult(ResponseEnum.FAILED);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.errorResult(ResponseEnum.FAILED);
        }
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    @Override
    public ResultData doGet(String url, Map<String, Object> map){
        try{
            URIBuilder uriBuilder = new URIBuilder(url);
            if (map != null) {
                // 遍历map,拼接请求参数
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
                }
            }
            // 调用不带参数的get请求
            return doGet(uriBuilder.build().toString());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.errorResult(ResponseEnum.FAILED);
        }
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ResultData doPost(String url, Map<String, Object> map){
        try {
            // 声明httpPost请求
            HttpPost httpPost = new HttpPost(url);
            // 加入配置信息
            httpPost.setConfig(config);

            // 判断map是否为空，不为空则进行遍历，封装from表单对象
            if (map != null) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
                // 构造from表单对象
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

                // 把表单放到post里
                httpPost.setEntity(urlEncodedFormEntity);
                httpPost.setHeaders((Header[]) map.get("heads"));
            }

            // 发起请求
            CloseableHttpResponse response = this.httpClient.execute(httpPost);
            return ResultUtil.successResult(new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                    response.getEntity(), "UTF-8")),ResponseEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.errorResult(ResponseEnum.FAILED);
        }
    }

    /**
     * 不带参数post请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    @Override
    public ResultData doPost(String url){
        return this.doPost(url, null);
    }
}
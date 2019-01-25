package com.mx.framework.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 网易云即时通讯工具类
 * @description:
 * @author:wuhairui
 * @date:2018/3/14
 */
@Log4j2
@Component
public class IMUtil {

    public static String appKey = "07410145bd738e90ecffa84f6c3ad117";
    public static String appSecret = "7b2e0dfc8616";

    /**
     *获取网易云通信API checksum校验头
     * @param token
     * @return
     */
    public Map<String , String > getPostHeader(String token){
        //生成随机字条串
        String nonce = CharacterUtils.getRandomString(32);
        String curtime = String.valueOf(System.currentTimeMillis()/1000);
        Map<String , String > mheader = new HashMap<>();
        mheader.put("AppKey",appKey);
        mheader.put("Nonce",nonce);
        mheader.put("CurTime",curtime);
        mheader.put("token",  "");
        mheader.put("CheckSum",CheckSumBuilder.getCheckSum(appSecret,nonce,curtime+""));
        return mheader;
    }

    /**
     * 发送请求到网页云信
     * @param url
     * @param param
     * @param header
     * @return
     */
    public String sendImServer( String url, String param,Map<String , String > header) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            Iterator<String> iter = header.keySet().iterator();
            // write header
            while (iter.hasNext()) {
                String key = iter.next();
                String value = header.get(key);
                conn.setRequestProperty(key, value);
            }

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            log.error(e.getMessage());
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                log.error(ex.getMessage());
            }
        }
        return result;
    }

    /**
     * 查询IM用户信息
     * @param userId
     * @param token
     * @return
     */
    public String getIMUserInfo(List<String> userId, String token){
        String url = "https://api.netease.im/nimserver/user/getUinfos.action";
        String para = "accids="+ JSON.toJSONString(userId);
        String result = sendImServer(url,para,getPostHeader(token));
        return result;
    }

    /**
     * IM中是否存在该用户
     * @param userId
     * @param token
     * @return
     */
    public Boolean existIMUser(long userId, String token){
        try {
            List<String> users = new ArrayList<>();
            users.add(userId+"");
            String result = getIMUserInfo(users,token);
            JSONObject jsonObject = JSON.parseObject(result);
            String code = String.valueOf(jsonObject.get("code"));
            if("200".equals(code)){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     *获取网易云群员信息API checksum校验头
     * @param
     * @return
     */
    public Map<String , String > getPostHeader(){
        //生成随机字条串
        String nonce = CharacterUtils.getRandomString(32);
        String curtime = String.valueOf(System.currentTimeMillis()/1000);
        Map<String , String > mheader = new HashMap<>();
        mheader.put("AppKey",appKey);
        mheader.put("Nonce",nonce);
        mheader.put("CurTime",curtime);
        mheader.put("CheckSum",CheckSumBuilder.getCheckSum(appSecret,nonce,curtime+""));
        return mheader;
    }

}

package com.mx.framework.api;

import com.mx.framework.ApiApplication;
import com.mx.framework.base.service.HttpAPIService;
import com.mx.framework.utils.IMUtil;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.message.BasicHeader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2019/1/17 9:47
 * Modified By :
 */
@SpringBootTest(classes = ApiApplication.class)
@RunWith(SpringRunner.class)
public class IMTest {

    @Autowired
    private HttpAPIService httpAPIService;

    @Autowired
    private IMUtil imUtil;

    @Test
    public void queryImUserInfo(){
        List<String> strings = new ArrayList<>();
        strings.add("6417");
        String imUserInfo = imUtil.getIMUserInfo(strings, "77C486B8D77086CA6B91A15A3CCF571B");
        System.out.println(imUserInfo);
    }
}

package com.exciting.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exciting.common.util.httpclient.OkHttpUtil;
import com.exciting.common.util.security.Base64Utils;
import com.exciting.common.util.security.RSAUtils;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;


public class OkHttpUtilTest {


    @Test
    public void test1() throws Exception {
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJzNNBhWsGsTP9KbGGPbTuqOH8J4bqyG84/6nlKXwqRsc3w4zQGjfAZr6fZa+Ve6sz6Wg6XmnQYBo4/qRcGMjnriVP4Ta3klWMvRHqpGcJ5dQauPWuidyczA/BQJafTTIZtnyMRjMLpl+mNx86rEN0lf9LFry7ANSkjt4DxMZjwnAgMBAAECgYB6V2l0K/9OLCC59CUdlHmIbQe+tjS0DD9xcTuieaJ2o9+fZrodsy8L25sainTNzHAaP1nMUZjmnNS/byyqDT8EzGqttnL5R6oJjSRdECyN7yN1zxTCk2lTz8EBK60UYmCg/tv6oSjqMVJ4mtAuzTrEhNJS0ar4czAVbUel6RtMUQJBANmGj+A76US/RnMU98CJok38AzBO0De9pOYFoCbIoEcKBxjlHqVYj8Z2aXdHLX5zcnwd5dLk5JTAu/m92hRY460CQQC4iRcm78LZkGSjpeXOdS1noQffJkfhBDgmotw8g8q456FymbRlD+5+kdrQBl38MTtPvnIaGWEiCwthOsTnAvmjAkB5qnnuwZPHj/SniaLJXjIZzEs8SdjMVJZW0e6xwqVjFojBs+VgNhi0uKZkwLsBbzEpms9hFgWD7gk1tqqzlsRBAkAg7h3ajeiYiILdDSrAN/rdRkgwUxV6mxUmF+PzTJVtf7A40iL18ezeW0rP2oMFEQgDh/m2nJbPy2bBeApRKx+/AkBzRRFp0M5sL+D6loIJv/0Y5Ki3f0BFWX1BFzqNGk7fb91f3PkjYPHnYY1uwZiWGo18g7HSIYvjV1Ro8veh/fVW";
        String url = "http://maicai.api.y.dingdongxiaoqu.com/balanceApi/cardOpen/";
        Map<String, String> paramsMap = new TreeMap<>();
        paramsMap.put("channel_id","5b57ec306c70ba47318b8118");

        JSONObject data = new JSONObject();
        data.put("serial_num","201904245455245654");
        JSONArray cardDatas = new JSONArray();
        JSONObject cardData = new JSONObject();
        cardData.put("card_id","5b57ebf76c70ba35318b80bf");
        cardData.put("count","1");
        cardDatas.add(cardData);
        data.put("data",cardDatas);

        byte[] dataByte = data.toJSONString().getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(dataByte, privateKey);
        paramsMap.put("data", Base64Utils.encode(encodedData));
        String post = OkHttpUtil.post(url, paramsMap);
        System.out.println(post);
    }

    @Test
    public void fuluTest() throws Exception {
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJzNNBhWsGsTP9KbGGPbTuqOH8J4bqyG84/6nlKXwqRsc3w4zQGjfAZr6fZa+Ve6sz6Wg6XmnQYBo4/qRcGMjnriVP4Ta3klWMvRHqpGcJ5dQauPWuidyczA/BQJafTTIZtnyMRjMLpl+mNx86rEN0lf9LFry7ANSkjt4DxMZjwnAgMBAAECgYB6V2l0K/9OLCC59CUdlHmIbQe+tjS0DD9xcTuieaJ2o9+fZrodsy8L25sainTNzHAaP1nMUZjmnNS/byyqDT8EzGqttnL5R6oJjSRdECyN7yN1zxTCk2lTz8EBK60UYmCg/tv6oSjqMVJ4mtAuzTrEhNJS0ar4czAVbUel6RtMUQJBANmGj+A76US/RnMU98CJok38AzBO0De9pOYFoCbIoEcKBxjlHqVYj8Z2aXdHLX5zcnwd5dLk5JTAu/m92hRY460CQQC4iRcm78LZkGSjpeXOdS1noQffJkfhBDgmotw8g8q456FymbRlD+5+kdrQBl38MTtPvnIaGWEiCwthOsTnAvmjAkB5qnnuwZPHj/SniaLJXjIZzEs8SdjMVJZW0e6xwqVjFojBs+VgNhi0uKZkwLsBbzEpms9hFgWD7gk1tqqzlsRBAkAg7h3ajeiYiILdDSrAN/rdRkgwUxV6mxUmF+PzTJVtf7A40iL18ezeW0rP2oMFEQgDh/m2nJbPy2bBeApRKx+/AkBzRRFp0M5sL+D6loIJv/0Y5Ki3f0BFWX1BFzqNGk7fb91f3PkjYPHnYY1uwZiWGo18g7HSIYvjV1Ro8veh/fVW";
        String url = "http://maicai.api.y.dingdongxiaoqu.com/balanceApi/cardOpen/";
        Map<String, String> paramsMap = new TreeMap<>();
        paramsMap.put("channel_id","5b57ec306c70ba47318b8118");

        JSONObject data = new JSONObject();
        data.put("serial_num","201904245455245654");
        JSONArray cardDatas = new JSONArray();
        JSONObject cardData = new JSONObject();
        cardData.put("card_id","5b57ebf76c70ba35318b80bf");
        cardData.put("count","1");
        cardDatas.add(cardData);
        data.put("data",cardDatas);

        byte[] dataByte = data.toJSONString().getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(dataByte, privateKey);
        paramsMap.put("data", Base64Utils.encode(encodedData));
        String post = OkHttpUtil.post(url, paramsMap);
        System.out.println(post);
    }

    public static void main(String[] args) throws Exception {
        /*String s = OkHttpUtil.get("https://www.linkedin.com/sales/search/people?doFetchHeroCard=false&functionIncluded=25&geoIncluded=102772228&logHistory=true&page=1&rsLogId=470945756&searchSessionId=NHce7a1JQPWZAsQ98a3E6g%3D%3D&seniorityIncluded=4%2C5%2C7");
        System.out.println(s);*/

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
    }

}
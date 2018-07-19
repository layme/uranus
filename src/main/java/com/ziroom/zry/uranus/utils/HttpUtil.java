package com.ziroom.zry.uranus.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author renhy
 * @version 1.0
 * @Date Created in 2018年07月02日 18:28
 * @since 1.0
 */
@Slf4j
@Component(value = "httpUtil")
public class HttpUtil {
    public final static String GET = "get";
    public final static String POST = "post";

    @Autowired
    private RestTemplate restTemplate;

    public <T> T baseHttp(String url, Object params, HttpHeaders headers, MediaType type, String method, Class<T> clz) {
        try {
            T result = null;
            switch (method) {
                case HttpUtil.POST:
                    if (Objects.isNull(type)) {
                        type = MediaType.APPLICATION_JSON_UTF8;
                    }
                    if (Objects.isNull(headers)) {
                        headers = new HttpHeaders();
                        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
                    }
                    headers.setContentType(type);
                    HttpEntity<Object> formEntity = new HttpEntity<>(params, headers);
                    result = restTemplate.postForObject(url, formEntity, clz);
                    break;
                case HttpUtil.GET:
                    result = restTemplate.getForObject(url, clz);
                    break;
                default:
                    throw new RuntimeException("method is not allowed.");
            }
            log.info("remote response is {}", result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException("远程调用失败，请联系管理员");
        }
    }

    public <T> T get(String url, Class<T> clz) {
        return this.baseHttp(url, null, null, null, HttpUtil.GET, clz);
    }

    public <T> T post(String url, Object params, Class<T> clz) {
        return this.baseHttp(url, params, null, null, HttpUtil.POST, clz);
    }

    public <T> T post(String url, Object params, HttpHeaders headers, Class<T> clz) {
        return this.baseHttp(url, params, headers, null, HttpUtil.POST, clz);
    }
    public <T> T post(String url, Object params, MediaType type, Class<T> clz) {
        return this.baseHttp(url, params, null, type, HttpUtil.POST, clz);
    }
    public <T> T post(String url, Object params, HttpHeaders headers, MediaType type, Class<T> clz) {
        return this.baseHttp(url, params, headers, type, HttpUtil.POST, clz);
    }
}

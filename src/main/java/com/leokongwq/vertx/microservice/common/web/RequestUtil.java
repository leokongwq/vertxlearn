package com.leokongwq.vertx.microservice.common.web;

import org.apache.commons.lang3.StringUtils;

import io.vertx.core.http.HttpServerRequest;

/**
 * @author : kongwenqiang
 * DateTime: 2017/12/1 下午3:18
 * Mail:kongwenqiang@qiyi.com   
 * Description: desc
 */
public final class RequestUtil {

    private RequestUtil() {
    }

    public static  Long getLong(HttpServerRequest request, String param, Long value) {
        if (StringUtils.isBlank(request.getParam(param))) {
            return value;
        }
        return Long.parseLong(request.getParam(param));
    }

    public static  Integer getInt(HttpServerRequest request, String param, Integer value) {
        if (StringUtils.isBlank(request.getParam(param))) {
            return value;
        }
        return Integer.parseInt(request.getParam(param));
    }

    public static  Float getFloat(HttpServerRequest request, String param, Float value) {
        if (StringUtils.isBlank(request.getParam(param))) {
            return value;
        }
        return Float.parseFloat(request.getParam(param));
    }

    public static  Double getDouble(HttpServerRequest request, String param, Double value) {
        if (StringUtils.isBlank(request.getParam(param))) {
            return value;
        }
        return Double.parseDouble(request.getParam(param));
    }
}

package com.ustb.evaluation.mod01common.utils.http;

import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.utils.security.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 响应工具类
 *
 * @author CaoChenLei
 */
public class ResponseUtil {
    private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * 向浏览器响应一个json字符串
     *
     * @param response 响应对象
     * @param status   状态码枚举对象
     * @param tip      提示信息
     * @param obj      返回的对象
     */
    public static void write(HttpServletResponse response, ResponseStatus status, String tip, Object obj) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(status.getCode());
            PrintWriter out = response.getWriter();
            out.write(JsonUtil.toString(new ApiResult(status.getCode(),  status.getMsg(), tip, obj)));
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("响应出错：" + status.getMsg(), e);
        }
    }
}
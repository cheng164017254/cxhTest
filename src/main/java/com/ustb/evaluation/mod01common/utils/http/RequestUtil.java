package com.ustb.evaluation.mod01common.utils.http;

import com.ustb.evaluation.mod01common.utils.security.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求工具类
 *
 * @author CaoChenLei
 */
public class RequestUtil {
    private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    /**
     * 从请求对象的输入流中获取指定类型对象
     *
     * @param request 请求对象
     * @param clazz   指定类型
     * @return 指定类型对象
     */
    public static <T> T read(HttpServletRequest request, Class<T> clazz) {
        try {
            return JsonUtil.toBean(request.getInputStream(), clazz);
        } catch (Exception e) {
            logger.error("读取出错：" + clazz, e);
            return null;
        }
    }
}
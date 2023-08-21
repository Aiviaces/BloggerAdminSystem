package com.hrbu.blog.blogadminsystem.Util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WebResponsUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 设置响应头的编码和内容类型-默认编码utf-8
     *
     * @param response    HttpServletResponse 对象
     * @param contentType 内容类型
     */
    public static void setResponseContentType(HttpServletResponse response, String contentType) {
        setResponseContentType(response, contentType, "UTF-8");
    }

    /**
     * 设置响应头的编码和内容类型
     *
     * @param response    HttpServletResponse 对象
     * @param contentType 内容类型
     * @param encoding    编码
     */
    public static void setResponseContentType(HttpServletResponse response, String contentType, String encoding) {
        response.setCharacterEncoding(encoding);
        response.setContentType(contentType);
    }

    /**
     * 发送 JSON 数组到前端
     *
     * @param response HttpServletResponse 对象
     * @param list     JSON 数组数据
     */
    public static void sendJsonArrayResponse(HttpServletResponse response, List<?> list) {
        setResponseContentType(response, "application/json");
        try {
            Writer out = response.getWriter();
            out.write(objectMapper.writeValueAsString(list));
            out.flush();
        } catch (IOException e) {
            // 处理异常
            throw new RuntimeException("发送Json格式失败", e);
        }
    }

    /**
     * 发送json响应
     *
     * @param response 响应
     * @param obj      发送对象
     */
    public static void sendJsonResponse(HttpServletResponse response, Object obj) {
        setResponseContentType(response, "application/json");
        try {
            if (response.isCommitted()) {
                throw new IllegalStateException("响应已经提交，无法发送Json格式数据");
            }
            Writer out = response.getWriter();
            out.write(objectMapper.writeValueAsString(obj));
            out.flush();
        } catch (IOException e) {
            // 处理异常
            throw new RuntimeException("发送Json格式失败", e);
        }
    }

    /**
     * 发送纯文本响应到前端
     *
     * @param response HttpServletResponse 对象
     * @param text     纯文本内容
     */
    public static void sendTextResponse(HttpServletResponse response, String text) {
        setResponseContentType(response, "text/plain");
        try {
            response.getWriter().write(text);
        } catch (IOException e) {
            throw new RuntimeException("Failed to send text response", e);
        }
    }

    /**
     * 发送错误信息响应到前端
     *
     * @param response HttpServletResponse 对象
     * @param status   HTTP 状态码
     * @param message  错误信息
     */
    public static void sendErrorResponse(HttpServletResponse response, int status, String message) {
        response.setStatus(status);
        sendTextResponse(response, message);
    }

    /**
     * 发送图片响应到前端
     *
     * @param response HttpServletResponse 对象
     * @param image    图片输入流
     * @param mimeType 图片 MIME 类型
     */
    public static void sendImageResponse(HttpServletResponse response, InputStream image, String mimeType) {
        setResponseContentType(response, mimeType);
        try {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = image.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to send image response", e);
        }
    }
}

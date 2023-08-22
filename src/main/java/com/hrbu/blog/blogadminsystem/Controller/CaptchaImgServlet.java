package com.hrbu.blog.blogadminsystem.Controller;

import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.WebParamIllegalException;
import com.hrbu.blog.blogadminsystem.Util.Generator.WebCaptchaGenerator;
import com.hrbu.blog.blogadminsystem.Util.WebRequestUtil;
import com.hrbu.blog.blogadminsystem.Util.WebResponsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.RemoteException;

@WebServlet(name = "CaptchaImgServlet", value = "/CaptchaImgServlet")
public class CaptchaImgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int width = WebRequestUtil.getRequestParameterInt(request, "width", false);
            int height = WebRequestUtil.getRequestParameterInt(request, "height", false);
            BufferedImage img = WebCaptchaGenerator.generateCaptcha(request, "captcha", width, height);
            WebResponsUtil.sendImageResponse(response, img, "png");
        } catch (WebParamIllegalException e) {
            throw new RemoteException("获取验证码前台数据错误.");
        }
    }
}

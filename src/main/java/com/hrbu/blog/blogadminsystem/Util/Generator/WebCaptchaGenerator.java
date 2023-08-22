package com.hrbu.blog.blogadminsystem.Util.Generator;

import javax.servlet.http.HttpServletRequest;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class WebCaptchaGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LINES = 20;

    /**
     * 生成验证码
     * 需要传入请求,以设置会话属性,避免其作为工具类的形式被破坏(不返回复杂参数了)
     *
     * @param request  请求
     * @param attrName 设置属性的 key
     * @return {@link BufferedImage} 返回图片
     */
    public static BufferedImage generateCaptcha(HttpServletRequest request, String attrName, int width, int height) {
        BufferedImage captchaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = captchaImage.createGraphics();

        // 设置背景颜色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // 添加干扰线
        Random random = new Random();
        for (int i = 0; i < LINES; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            Color randomLineColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            g2d.setColor(randomLineColor);
            g2d.drawLine(x1, y1, x2, y2);
        }

        // 设置字体和颜色
        int fontHeight = (int) (height * 0.8);
        g2d.setFont(new Font("Arial", Font.BOLD, fontHeight));

        // 生成随机验证码
        int x = width / 6;
        StringBuilder captchaText = new StringBuilder();
        for (int i = 0; i < 6; i++) { // 生成6位的验证码
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            Color randomCharColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            g2d.setColor(randomCharColor);
            g2d.drawString(String.valueOf(randomChar), x * i + 10, fontHeight);

            captchaText.append(randomChar);
        }

        g2d.dispose();

        request.getSession().setAttribute(attrName, captchaText.toString());

        return captchaImage;
    }
}
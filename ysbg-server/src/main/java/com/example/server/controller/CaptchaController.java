package com.example.server.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "验证码接口")
    @GetMapping(value = "/captcha", produces = "image/jpeg")    // 固定响应类型
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        // 定义response 输出类型为 image/jpeg类型
        response.setDateHeader("Expires", 0);
        // 设置响应头
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // 响应格式
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // 设置响头
        response.setHeader("Pragma", "no-cache");
        // 返回一个图片
        response.setContentType("image/jpeg");

        // -----------------------生成验证码 begin------------------------------
        //获取验证码文本内容
        String text = defaultKaptcha.createText();
        System.out.println("验证码:  " + text);
        //将验证码放到session中
        request.getSession().setAttribute("captcha",text);
        //根据文本内容创建图形验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //输出流输出图片,格式为jpg
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // -----------------------生成验证码 end------------------------------
    }
}

package com.elf.core.web;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;

/**
 * @Title: PageController
 * @Description: 视图控制器,返回jsp视图给前端
 * @Author:李一鸣(liyiming.neu@neusoft.com)
 * @Date:2017年10月24日
 */
@Controller
public class PageController extends BaseController {

    @Autowired
    private Producer captchaProducer;

    /**
     * <br>Description: 验证码
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2018年3月11日
     * @return
     */
    @GetMapping("/api/kaptcha.jpg")
    public void getKaptchaImage() throws Exception {

        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();
        // store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        // print the kaptcha text
        logger.info(Constants.KAPTCHA_SESSION_KEY + " : " + capText);
        // create the image with the text
        BufferedImage bufferedImage = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bufferedImage, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * <br>Description: 页面跳转
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月23日
     * @param path
     * @return
     */
    @GetMapping(value = "/page/{path}")
    public String forward(@PathVariable String path) {
        return path.replace("_", "/");
    }

}

package seclab.service;

import com.google.code.kaptcha.Producer;
import seclab.domain.Result;
import seclab.domain.entity.User;
import seclab.domain.repository.UserRepository;
import seclab.utils.MD5;
import seclab.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static seclab.config.Constants.SESSION_KEY_PREFIX;

/**
 * User: Harlan1994
 * Date: 2017/9/19
 * Time: 16:31
 * Description:
 */
@Service
public class UserService {

    // 登录结果码
    private static final Integer LOGIN_CODE_SUCCESS = 0xf1;
    private static final Integer LOGIN_CODE_UNEXIST = 0xf2;
    private static final Integer LOGIN_CODE_FAILURE = 0xf3;

    // 注册结果码
    private static final Integer REGISTER_CODE_SUCCESS = 0xff1;
    private static final Integer REGISTER_CODE_FAILURE = 0xff2;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Producer kaptchaProducer;

    /**
     * 用户登录逻辑
     * 登录验证结果用Result封装返回，并交由controller处理
     * 数据库保存的密码为: MD5(MD5(rawPassword)+randomString)
     *
     * @param user
     * @param randomString
     * @return
     */
    public Result login(User user, String randomString, HttpServletRequest request) {
        // 数据库保存的密码为MD5加密过的
        User account = userRepository.findByUsername(user.getUsername());
        if (account != null) {
            if (account.getPassword().equals(MD5.encrypt(MD5.encrypt(user.getPassword()) + randomString))) {
                // TODO: 2017/9/21 登录成功，生成token
                return ResultUtil.success(LOGIN_CODE_SUCCESS, "登陆成功");
            }
            return ResultUtil.error(LOGIN_CODE_UNEXIST, "密码错误");
        } else {
            return ResultUtil.error(LOGIN_CODE_FAILURE, "用户不存在");
        }
    }

    public String verifycode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

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
        String capText = kaptchaProducer.createText();

        String code = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        System.out.println("******************验证码是: " + code + "******************");

        // store the text in the session
        session.setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = kaptchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "png", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    public User get(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    /**
     * 注册一个用户
     */
    public Result register(User user) {
        User result = userRepository.save(user);
        if (result != null) {
            return ResultUtil.success(REGISTER_CODE_SUCCESS, "注册成功");
        }
        return ResultUtil.error(REGISTER_CODE_FAILURE, "注册失败");
    }

    public void removeSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute(SESSION_KEY_PREFIX);
    }

    public void addSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_KEY_PREFIX, user);
    }
}
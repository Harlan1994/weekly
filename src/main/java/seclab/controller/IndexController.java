package seclab.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import seclab.domain.Result;
import seclab.domain.entity.User;
import seclab.service.UserService;
import seclab.utils.CookieUtil;
import seclab.utils.JwtUtil;
import seclab.utils.RandomUtil;
import seclab.utils.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static seclab.config.Constants.KEY_JWT_TOKEN;

/**
 * User: Harlan1994
 * Date: 2017/8/6
 * Time: 15:49
 * Description:
 */
//@LoginAuth
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute(RandomUtil.getRandomString(6));
        return "pages/front/index.html";
    }

    /**
     * 登录界面
     *
     * @return
     */
    @GetMapping(value = "/login")
    public String login(Model model) {
//        String randomString = RandomUtil.getRandomString(6);
//        model.addAttribute("randomString", randomString);
        return "pages/front/login.html";
    }

    /**
     * 登录逻辑
     * json数据格式
     *
     * @param user
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public @ResponseBody
    Result doLogin(HttpServletRequest request,
                   HttpServletResponse response,
                   @Valid User user,
                   @RequestParam String verifyCode,
                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(-3, bindingResult.getFieldError().getDefaultMessage());
        }

        if(!StringUtils.isEmpty(verifyCode) || verifyCode.length() != 4){
            return ResultUtil.error(-4, "验证码错误!");
        }
        return userService.login(request, response, user);
    }

    /**
     * 登录逻辑
     * json数据格式
     *
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/doRegister")
    public @ResponseBody
    Result doRegister(HttpServletRequest request,
                      HttpServletResponse response,
                      @Valid User user,
                      @RequestParam String verifyCode,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(-3, bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.register(user);
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        request.getSession().removeAttribute(SESSION_KEY_PREFIX);
        JwtUtil.invalidateRelatedTokens(request);
        CookieUtil.clear(response, KEY_JWT_TOKEN);
        return "redirect:/login";
    }

    @GetMapping("/captcha")
    public String captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return userService.captcha(request, response);
    }
}
package seclab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import seclab.annotation.LoginAuth;
import seclab.domain.Result;
import seclab.domain.entity.User;
import seclab.service.UserService;
import seclab.utils.RandomUtil;
import seclab.utils.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static seclab.config.Constants.SESSION_KEY_PREFIX;

/**
 * User: Harlan1994
 * Date: 2017/8/6
 * Time: 15:49
 * Description:
 */
@LoginAuth
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
    @GetMapping(value = "/", produces = "text/html; charset=UTF-8")
    public String index(Model model) {
        model.addAttribute(RandomUtil.getRandomString(6));
        return "front/index.html";
    }

    /**
     * 登录界面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
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
    @PostMapping(value = "/doLogin")
    public @ResponseBody
    Result doLogin(HttpServletRequest request, @Valid User user, BindingResult bindingResult) {

        System.out.println("----running doLogin----");

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(-3, bindingResult.getFieldError().getDefaultMessage());
        }

        return userService.login(user, request);
    }

    /**
     * 登录逻辑
     * json数据格式
     *
     * @param user
     * @param bindingResult
     * @param randomString
     * @return
     */
    @PostMapping(value = "/doRegister")
    public @ResponseBody
    Result doRegister(HttpServletRequest request, @Valid User user, BindingResult bindingResult, @RequestParam String randomString) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(-3, bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.login(user, request);
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_KEY_PREFIX);
        return "redirect:/login";
    }

    @GetMapping("/captcha")
    public String captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return userService.captcha(request, response);
    }
}
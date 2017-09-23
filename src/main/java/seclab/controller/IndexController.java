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

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "front/writer";
    }


    /**
     * 登录界面
     *
     * @return
     */
    @GetMapping(value = "/login")
    public String login(Model model) {
        String randomString = RandomUtil.getRandomString(6);
        model.addAttribute("randomString", randomString);
        return "front/login";
    }

    /**
     * 登录
     *
     * @param user
     * @param bindingResult
     * @param randomString
     * @return
     */
    @PostMapping(value = "/doLogin")
    @ResponseBody
    public Result doLogin(HttpServletRequest request, @Valid User user, BindingResult bindingResult, @RequestParam String randomString) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(-3, bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.login(user, randomString, request);
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        userService.removeSession(request);
        return "redirect:/login";
    }

    @GetMapping("/verifycode")
    public String verifycode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return userService.verifycode(request, response);
    }
}
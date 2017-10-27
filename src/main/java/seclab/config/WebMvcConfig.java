package seclab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import seclab.interceptor.LoginInterceptor;

/**
 * User: Harlan1994
 * Date: 2017/9/25
 * Time: 16:55
 * Description:
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/captcha")
                .excludePathPatterns("/logout");
        super.addInterceptors(registry);
    }
}
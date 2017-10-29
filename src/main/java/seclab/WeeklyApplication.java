package seclab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import seclab.jwt.JwtFilter;

import java.util.Collections;

@SpringBootApplication
public class WeeklyApplication {
    @Value("${services.auth}")
    private String authService;

    public static void main(String[] args) {
        SpringApplication.run(WeeklyApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
        registrationBean.addUrlPatterns("/", "/logout");

        return registrationBean;
    }
}

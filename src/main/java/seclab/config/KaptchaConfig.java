package seclab.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * User: Harlan1994
 * Date: 2017/8/23
 * Time: 10:20
 * Description:
 */
@Configuration
public class KaptchaConfig {

    @Value("${kaptcha.border}")
    private String border;
    @Value("${kaptcha.border.color}")
    private String borderColor;
    @Value("${kaptcha.textproducer.font.color}")
    private String fontColor;
    @Value("${kaptcha.image.width}")
    private String imageWidth;
    @Value("${kaptcha.image.height}")
    private String imageHeight;
    @Value("${kaptcha.session.key}")
    private String sessionKey;
    @Value("${kaptcha.textproducer.char.length}")
    private String charLength;
    @Value("${kaptcha.textproducer.font.names}")
    private String fontNames;

    @Bean(name = "kaptchaProducer")
    public DefaultKaptcha getKaptchaBean() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", border);
        properties.setProperty("kaptcha.border.color", borderColor);
        properties.setProperty("kaptcha.textproducer.font.color", fontColor);
        properties.setProperty("kaptcha.image.width", imageWidth);
        properties.setProperty("kaptcha.image.height", imageHeight);
        properties.setProperty("kaptcha.session.key", sessionKey);
        properties.setProperty("kaptcha.textproducer.char.length", charLength);
        properties.setProperty("kaptcha.textproducer.font.names", fontNames);
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
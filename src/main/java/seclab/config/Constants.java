package seclab.config;

/**
 * User: Harlan1994
 * Date: 2017/8/6
 * Time: 15:38
 * Description:
 */
public class Constants {
    public static final String SESSION_KEY_PREFIX = "session:user";
    public static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER = "Authorization";

    public static final String KEY_JWT_TOKEN = "key_jwt_token";
    public static final String KEY_SIGN_IN = "key_sign_key";

    public static final String REDIS_SET_ACTIVE_SUBJECTS = "redis_set_active_subjects";
}
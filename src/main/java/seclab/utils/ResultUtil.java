package seclab.utils;



import seclab.domain.Result;
import seclab.enums.ResultCode;

import javax.validation.constraints.NotNull;

/**
 * 统一返回的请求结果
 * 支持登录返回结果，需要有实体类json数据的结果
 * 页面请求的结果等
 */
public class ResultUtil {

    private static final String DEFAULT_SUCCESS_MSG = "success";

    public static Result success() {
        return success(null);
    }

    /**
     * 只需要请求结果
     *
     * @param code
     * @param message
     * @return
     */
    public static Result success(Integer code, String message) {
        return new Result()
                .setCode(code)
                .setMessage(message);
    }

    /**
     * 有数据的请求结果
     *
     * @param data
     * @return
     */
    public static Result success(@NotNull Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MSG)
                .setData(data);
    }

    /**
     * 错误类型，定义好的
     *
     * @param resultEnum
     * @return
     */
    public static Result error(ResultCode resultEnum) {
        return new Result()
                .setCode(resultEnum.getCode())
                .setMessage(resultEnum.getMsg());
    }

    /**
     * 其他错误
     *
     * @param code
     * @param message
     * @return
     */
    public static Result error(Integer code, String message) {
        return new Result()
                .setCode(code)
                .setMessage(message);
    }
}

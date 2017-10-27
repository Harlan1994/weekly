package seclab.domain;

import com.alibaba.fastjson.JSON;
import lombok.NoArgsConstructor;
import seclab.enums.ResultCode;

/**
 * 请求放回的最外层对象
 */
@NoArgsConstructor
public class Result<T> {
    // 结果码
    private Integer code;

    // 提示信息
    private String message;

    private T data;

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Result setCode(ResultCode code) {
        this.code = code.getCode();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

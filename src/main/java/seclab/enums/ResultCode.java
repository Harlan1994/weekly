package seclab.enums;

/**
 * User: Harlan1994
 * Date: 2017/7/28
 * Time: 18:53
 * Description:
 */
public enum ResultCode {
    SUCCESS(200, "success"), //成功
    FAIL(400, "failed"), //失败
    UNAUTHORIZED(401, "unauthorized"),//未认证（签名错误）
    NOT_FOUND(404, "interface not exist"),//接口不存在
    INTERNAL_SERVER_ERROR(500, "server failed");//服务器内部错误

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
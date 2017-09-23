package seclab.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成32位小写字母的MD5加密
 */
public class MD5 {
    public static String encrypt(String context) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(context.getBytes());//update处理
        byte[] encryptedContext = md.digest();//调用该方法完成计算

        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < encryptedContext.length; offset++) {//做相应的转化（十六进制）
            i = encryptedContext[offset];
            if (i < 0) i += 256;
            if (i < 16) buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }
}

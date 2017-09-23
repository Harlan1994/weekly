package seclab.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();

    /**
     * 生成指定位数的字符串（小写）
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            stringBuffer.append(base.charAt(number));
        }
        return stringBuffer.toString();
    }

    public static List getRandomList(List list, int count){
        List randomList = new ArrayList();

        for (int num = 0; num < count; num++){
            int index = random.nextInt(list.size());
            randomList.add(list.get(index));
            list.remove(index);
        }

        return randomList;
    }
}

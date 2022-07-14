package cn.tedu.nybike.socker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        String dateFormat = format.format(new Date());
        System.out.println(dateFormat);
    }
}

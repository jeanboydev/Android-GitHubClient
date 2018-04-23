package com.jeanboy.arch.base.util;

import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jeanboy on 2016/7/8.
 */
public class StringUtil {

    public StringUtil() {
        throw new AssertionError();
    }

    /**
     * MD5
     *
     * @param inStr
     * @return
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 格式化价格
     *
     * @param money
     * @return
     */
    public static String formatPrice(long money) {
        if (money >= 100) {
            StringBuilder builder = new StringBuilder();
            builder.append(money).insert((money + "").length() - 2, ".");
            return builder.toString();
        } else if (money >= 10) {
            return "0." + money;
        } else {
            return "0.0" + money;
        }
    }

    /**
     * 格式化数量
     *
     * @param count
     * @return
     */
    public static String formatCount(int count) {
        if (count >= 10000) {
            return Math.round(count * 100 / 10000) / 100.0 + "w";
        } else if (count >= 1000) {
            return Math.round(count * 100 / 1000) / 100.0 + "k";
        } else {
            return count + "";
        }
    }

    private static final int SIZE_K = 1024;
    private static final int SIZE_M = 1024 * SIZE_K;
    private static final int SIZE_G = 1024 * SIZE_M;

    /**
     * 格式化容量
     *
     * @param byteSize
     * @return
     */
    public static String formatSize(long byteSize) {
        if (byteSize > SIZE_G) {
            return (Math.round(byteSize * 100 / SIZE_G) / 100.0) + "G";
        } else if (byteSize > SIZE_M) {
            return (Math.round(byteSize * 100 / SIZE_M) / 100.0) + "M";
        } else {
            return (Math.round(byteSize * 100 / SIZE_K) / 100.0) + "K";
        }
    }

    /**
     * double 保留两位小数
     *
     * @param d
     * @return
     */
    public static String formatDecimal2(double d) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setRoundingMode(RoundingMode.HALF_UP);
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(2);
        return nf.format(d);
    }

}

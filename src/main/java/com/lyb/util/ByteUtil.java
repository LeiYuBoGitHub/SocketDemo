package com.lyb.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/1 18:12
 * @Description:
 */
public class ByteUtil {

    /**
     * int类型 转 4 byte
     */
    public static byte[] intTo4Byte(int res){
        byte[]bytes=new byte[4];
        bytes[0]=(byte) ((res>>24)&0xff);
        bytes[1]=(byte) ((res>>16)&0xff);
        bytes[2]=(byte) ((res>>8)&0xff);
        bytes[3]=(byte) (res&0xff);
        return bytes;
    }

    private static byte[] replaceByte(byte[] bytes) {

        List<Byte> list = new ArrayList<>();

        for (byte b : bytes) {
            if (b != 0) {
                list.add(b);
            }
        }

        byte[] byteData = new byte[list.size()];

        for (int i = 0; i < list.size();i++) {
            byteData[i] = list.get(i);
        }

        return byteData;
    }
}

package com.lyb.client;

import com.lyb.config.Constant;
import com.lyb.util.ByteUtil;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

/**
 * @Auther: 野性的呼唤
 * @Date: 2018/9/11 13:50
 * @Description:
 */
public class Client {

    public static void main(String[] args){
        start();
    }

    private static void start() {

        Socket socket = null;

        try {

            socket = new Socket(Constant.HOST, Constant.PORT);

            for (int i = 0; i < 1; i++) {

                OutputStream os = socket.getOutputStream();

                DataOutputStream out = new DataOutputStream(os);

                out.writeUTF("服务端你好,我是客户端");

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataIn = new DataInputStream(inputStream);
                System.out.println("服务端消息:" + dataIn.readUTF());

                socket.close();
                //byte[] data = sendData();
                //os.write(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static byte[] sendData() {

        String message = "野性的呼唤";

        int dataLength = message.getBytes().length;

        byte[] dataLengthBytes = ByteUtil.intTo4Byte(dataLength);

        byte[] headBytes = ByteUtil.intTo4Byte(1);

        int length = dataLength + dataLengthBytes.length + headBytes.length;

        byte[] data = new byte[length];

        int x = 0;

        for (int i = 0;i < 4; i++) {
            data[x] = headBytes[i];
            x++;
        }

        for (int i = 0;i < 4; i++) {
            data[x] = dataLengthBytes[i];
            x++;
        }

        for (int i = 0;i < message.getBytes().length; i++) {
            data[x] = message.getBytes()[i];
            x++;
        }

        System.out.println("发送的数据总长度:" + length);

        System.out.println("发送的字节数组:" + Arrays.toString(data));

        return data;

    }

}

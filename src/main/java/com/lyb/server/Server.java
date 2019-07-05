package com.lyb.server;

import com.lyb.config.Constant;
import com.lyb.util.ByteUtil;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: 野性的呼唤
 * @Date: 2018/9/11 13:50
 * @Description:
 */
public class Server {

    public static void main(String[] args){
        start();
    }

    private static void start(){

        ServerSocket serverSocket = null;

        Socket socket = null;

        try {

            serverSocket = new ServerSocket(Constant.PORT);

            int i = 0;

            do {

                socket = serverSocket.accept();

                System.out.println("客户端已连接......");

                //接收信息
                InputStream inputStream = socket.getInputStream();

                DataInputStream dataInputStream = new DataInputStream(inputStream);

                System.out.println("客户端消息:" + dataInputStream.readUTF());

                //发送给客户端的响应
                OutputStream os = socket.getOutputStream();
                DataOutputStream out = new DataOutputStream(os);
                out.writeUTF("客户端你好,我是服务端");


            } while (true);


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

            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*private String decoder(byte[] bytes) {

    }*/

    //读取基本字节,包含头和长度信息
    private byte[] readBasisByte(byte[] bytes) {

        //读取头
        return readByte(bytes);

    }

    //读取长度字节
    private byte[] readLengthByte(byte[] bytes) {

        //读取长度
        return readByte(bytes);

    }

    private byte[] readByte(byte[] bytes) {

        byte[] data = new byte[bytes.length-4];

        int x = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (i > 3) {
                data[x] = bytes[i];
            }
        }

        return data;
    }
}

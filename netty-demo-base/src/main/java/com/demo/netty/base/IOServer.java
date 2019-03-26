package com.demo.netty.base;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author ddmc
 * @Create 2019-03-26 20:24
 */
public class IOServer {
    public static void main(String[] args) throws Exception {

        final ServerSocket serverSocket = new ServerSocket(8000);

        // (1) 接收新连接线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // (1) 阻塞方法获取新的连接
                        final Socket socket = serverSocket.accept();

                        // (2) 每一个新的连接都创建一个线程，负责读取数据
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    byte[] data = new byte[1024];
                                    InputStream inputStream = socket.getInputStream();
                                    while (true) {
                                        int len;
                                        // (3) 按字节流方式读取数据
                                        while ((len = inputStream.read(data)) != -1) {
                                            System.out.println(new String(data, 0, len));
                                        }
                                    }
                                } catch (IOException e) {
                                }
                            }
                        }).start();

                    } catch (IOException e) {
                    }

                }
            }
        }).start();
    }
}
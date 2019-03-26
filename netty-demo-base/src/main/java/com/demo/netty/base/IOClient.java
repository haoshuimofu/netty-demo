package com.demo.netty.base;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Author ddmc
 * @Create 2019-03-26 20:25
 */
public class IOClient {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("127.0.0.1", 8000);
                    while (true) {
                        try {
                            socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                            socket.getOutputStream().flush();
                            Thread.sleep(2000);
                        } catch (Exception e) {
                        }
                    }
                } catch (IOException e) {
                }
            }
        }).start();
    }
}
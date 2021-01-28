package com.zhx.javalib.net.nio;

public class NioServer {
    public static final String DEFAULT_SERVER_IP = "127.0.0.1";
    public static final int DEFAULT_PORT = 12345;

    private static NioServerHandle nioServerHandle;

    public static void main(String[] args) {
        nioServerHandle = new NioServerHandle(DEFAULT_PORT);
        new Thread(nioServerHandle, "Server").start();
    }
}

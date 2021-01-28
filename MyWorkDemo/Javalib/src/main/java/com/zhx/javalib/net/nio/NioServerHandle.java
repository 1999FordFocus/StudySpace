package com.zhx.javalib.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioServerHandle implements Runnable {

    private volatile boolean started;
    private ServerSocketChannel serverChannel;
    private Selector selector;

    public NioServerHandle(int port) {
        try {
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port));
            //注册OP_ACCEPT事件，表明serverChannel对客户端连接事件感兴趣
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            started = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (started) {
            try {
                selector.select(1000);//阻塞式，每1s唤醒一次，查询是否有事件产生
                Set<SelectionKey> selectionKeys = selector.selectedKeys();//事件集合
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //退出循环的时候，selector也要关闭
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
            //读数据
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(buffer);
                if (readBytes > 0) {
                    //因为channel写入了buffer，现在要把buffer切换模式
                    buffer.flip();
                    byte[] bytes = new byte[readBytes];
                    buffer.get(bytes);
                    String message = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("服务器收到消息：" + message);
                    String rspString = response(message);
                    doWrite(sc, rspString);
                } else if (readBytes < 0) {
                    //链路已经关闭，释放资源
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private String response(String message) {
        return "response ," + message + ",Now is " + new java.util.Date(
                System.currentTimeMillis()).toString();
    }

    private void doWrite(SocketChannel sc, String responseStr) throws IOException {
        byte[] bytes = responseStr.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        int count = sc.write(writeBuffer);
        System.out.println("write :" + count + "byte'");
    }
}

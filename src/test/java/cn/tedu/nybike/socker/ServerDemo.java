package cn.tedu.nybike.socker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 服务器端：使用Socket+IO流
 *      服务器端启动的时候，需要监听指定端口号
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        // 指定服务器监听 65000 端口号
        ServerSocket server = new ServerSocket(65000);
        System.out.println("服务器已经启动成功,等待客户端的连接.......");

        Socket socket = server.accept();
        System.out.println("有客户端连接成功!!!!!");

        InputStream in = socket.getInputStream();
        // 先创建保存数据的字节数组
        byte[] arr = new byte[1024];
        // 读取数据到字节数组中, 本次读取到了多少个数据
        int read = in.read(arr);
        System.out.println("客户端的请求为：" + new String(arr, 0, read));

        String dateStr = new Date().toString();
        OutputStream out = socket.getOutputStream();
        out.write(dateStr.getBytes("utf-8"));
        out.flush();

    }
}

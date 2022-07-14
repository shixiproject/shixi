package cn.tedu.nybike.socker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端：
 *      客户端连接服务器端的时候，需要指定IP+PORT
 */
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 65000);
        // 发送请求
        OutputStream out = socket.getOutputStream();
        String reqStr = "现在的时间是多少?";
        out.write(reqStr.getBytes("utf-8"));
        out.flush();

        InputStream in = socket.getInputStream();
        byte[] arr = new byte[1024];
        int read = in.read(arr);
        System.out.println("服务器端的响应信息为："+new String(arr, 0, read));
    }
}

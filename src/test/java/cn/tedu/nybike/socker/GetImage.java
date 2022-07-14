package cn.tedu.nybike.socker;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 通过Socket爬取网络中的数据
 *      使用Socket获取网络中的数据，是通过HTTP协议实现的。
 *      需要用户手动指定HTTP协议相关内容
 */
public class GetImage {
    public static void main(String[] args) throws IOException {
        // 图片地址
        String imgUrl = "https://pic4.zhimg.com/v2-d7db0d2d222141ef1ca1b11c273a0c12_r.jpg";
        // 连接服务器端
        Socket socket = new Socket("pic4.zhimg.com", 80);
        // 发起HTTP请求
        StringBuffer strBuf = new StringBuffer();   // 保存Http请求信息
        // 将请求信息封装到Strbuf
        strBuf.append("GET "+imgUrl+" HTTP/1.1\r\n");  // 封装请求行
        strBuf.append("Host: pic4.zhimg.com\r\n");
        strBuf.append("accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n");
        strBuf.append("accept-encoding: gzip, deflate, br\r\n");
        strBuf.append("accept-language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\r\n");
        strBuf.append("\r\n");

        // 发起请求
        OutputStream out = socket.getOutputStream();
        out.write(strBuf.toString().getBytes());
        out.flush();

        // 客户端接受响应信息
        InputStream in = socket.getInputStream();
        byte[] arr = new byte[1024*80];
        // num通过输入流读取到多少个字节数据
        int num = in.read(arr);
        System.out.println(new String(arr, 0, num));

    }

    @Test
    public void test() throws IOException {
        String imgUrl = "https://pic4.zhimg.com/v2-d7db0d2d222141ef1ca1b11c273a0c12_r.jpg";
        // 创建HTTPClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建HttpGet对象（专门发起Http get请求）
        HttpGet get = new HttpGet(imgUrl);
        // 发起请求返回响应数据
        CloseableHttpResponse response = client.execute(get);
        // 判断响应的状态码是否为200
        if(response.getStatusLine().getStatusCode()==200){
            // 直接获取响应正文中的数据（图片的数据）
            HttpEntity entity = response.getEntity();       // 图片数据
            byte[] bytes = EntityUtils.toByteArray(entity);
            OutputStream out = new FileOutputStream("abc.jpg");
            out.write(bytes);
            out.flush();
            out.close();
        }
    }
}

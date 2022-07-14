package cn.tedu.nybike.hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
public class Demo01 {
    /**
     * 从HDFS中查询 / 下的所有内容
     */
    @Test
    public void test1() throws Exception {
        // 创建配置信息对象
        Configuration conf = new Configuration();
        // 创建Hadoop的文件系统对象
        // Hadoop可以使用多种文件系统：本地文件系统、HDFS、S3.。。。
        // uri用来指定使用的文件系统的Schema  file:///
        URI uri = new URI("hdfs://192.168.137.100:9000");
        FileSystem fs = FileSystem.get(uri, conf);

        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            System.err.println(fileStatus);
        }
    }

    /**
     * 使用IO拷贝的方式，实现本地文件上传到HDFS文件系统
     */
    @Test
    public void test2() throws Exception {
        // 读取本地文件
        InputStream in = new FileInputStream("D:/wc.txt");

        // 连接HDFS，获取文件系统对象
        Configuration conf = new Configuration();
        URI uri = new URI("hdfs://192.168.137.100:9000");
        FileSystem fs = FileSystem.get(uri, conf);
        // 创建对应的输出流
        FSDataOutputStream out = fs.create(new Path("/a.txt"));

        IOUtils.copy(in, out); // 将输入流中的数据交给输出流即可实现文件的上传
    }

    /**
     * 文件下载：IO拷贝
     *      源文件：HDFS， 目标路径：本地文件系统
     */
    @Test
    public void test3() throws Exception {
        // 输入流：HDFS
        Configuration conf = new Configuration();
        URI uri = new URI("hdfs://192.168.137.100:9000");
        FileSystem fs = FileSystem.get(uri, conf);
        // 获取输入流 :
        FSDataInputStream in = fs.open(new Path("/a.txt"));

        OutputStream out = new FileOutputStream("abc.txt");
        // 将输入流中的数据拷贝给输出流
        IOUtils.copy(in, out);
    }


    @Autowired
    private FileSystem fs;

    @Test
    public void test4() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            // fileStatus.getPath() 获取文件的路径名称
            System.err.println(fileStatus.getPath());
        }
    }
    // 文件上传：本地路径（源文件路径）---》HDFS路径（目标路径）
    @Test
    public void test5() throws IOException {
        // 源文件路径
        Path srcPath = new Path("D:/wc.txt");
        // 目标文件路径
        Path dstPath = new Path("/a200.txt");
        fs.copyFromLocalFile(srcPath, dstPath);
    }


}

package cn.tedu.nybike;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class NybikeApplicationTests {

    @Autowired
    private FileSystem fs;

    @Test
    void contextLoads() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            // fileStatus.getPath() 获取文件的路径名称
            System.err.println(fileStatus.getPath());
        }
    }

}

package cn.tedu.nybike.conf;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * HDFS的配置对象，用来创建FileSystem对象
 */
@Configuration
public class HDFSConf {

    @Bean
    public org.apache.hadoop.conf.Configuration getConf(){
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();

        // 设置HDFS数据块副本数量为1
        conf.set("dfs.replication", "1");

        return conf;
    }

    @Bean
    public FileSystem getFS(){
        FileSystem fs = null;
        try {
            URI uri = new URI("hdfs://192.168.137.100:9000");
            fs = FileSystem.get(uri, getConf(), "root");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return fs;
    }
}

package org.example.test;




import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.net.URI;

import java.util.HashMap;


public class Hdfstest {


    static String hadoop1url="10.1.75.45";


    /**
     * https://zhuanlan.zhihu.com/p/76290373 ref
     * @throws Exception
     */
    @Test
    public  void hdfstest() throws  Exception
    {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", String.format("hdfs://%s:9000", hadoop1url));
        /**
         * 参数优先级： 1、客户端代码中设置的值 2、classpath下的用户自定义配置文件 3、然后是服务器的默认配置
         */
        conf.set("dfs.replication", "3");

        FileSystem fs = FileSystem.get(new URI(String.format("hdfs://%s:9000", hadoop1url)),conf);

        //命令测试
        RemoteIterator<LocatedFileStatus> listFiles=fs.listFiles(new Path("/"),true);


        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
//            System.out.println(fileStatus.getPath().getName());
//            System.out.println(fileStatus.getBlockSize());
//            System.out.println(fileStatus.getPermission());
//            System.out.println(fileStatus.getLen());
            HashMap<String,String> dic=new HashMap<>();
            dic.put("filename:",fileStatus.getPath().getName());
            dic.put("blocksize:", String.valueOf(fileStatus.getBlockSize()));
            dic.put("permission:",String.valueOf(fileStatus.getPermission()));
            dic.put("length:",String.valueOf(fileStatus.getLen()));
            System.out.println(dic);
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation bl : blockLocations) {
                System.out.println("block-length:" + bl.getLength() + ";" + "block-offset:" + bl.getOffset());
                String[] hosts = bl.getHosts();
                System.out.print("hosts:");
                for (String host : hosts) {
                    System.out.print(host);
                    System.out.print(",");
                }
                System.out.print("\n");
            }

            System.out.println("----------------------------------------");

        }
        System.out.println("----文件递归迭代完了------");


        fs.close();
    }

}

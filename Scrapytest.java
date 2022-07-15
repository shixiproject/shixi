/**
 * 请改包名
 */
package org.example.test;

import com.alibaba.fastjson.JSONArray;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


@Component
public class Scrapytest
{





    @Test
    public  void  scrapytest() throws  Exception
    {
        System.out.println("csv:--------------------------------------");

       System.out.println(Scrapier.scrapyOneShot(12));
    }
    @Test
    public void savetodfstest() throws Exception
    {


        String scrapydata;

        //数据爬取
        if(false)
        {
            scrapydata= Scrapier.scrapyOneShot(50);
        }
        else
        {
            scrapydata="test_data\nwwwwww";
        }





        //保存到hdfs
        //remember to close fs after using
        FileSystem fs = Scrapier.createnewDefaultFS();

        //csv保存在 hdfs的路径
        String saved_hdfspath = Scrapier.savetoHdfs(scrapydata,fs);

        fs.close();
        //sql 执行语句 用于导入path对应的文件到hive 类似一下语句
        //load data inpath '${path}' into table crimedatabase.crimedata;
        String sql_string=Scrapier.generateSqlLoadToHivedatabase(saved_hdfspath);
    }

}

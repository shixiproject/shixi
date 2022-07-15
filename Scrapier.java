package org.example.test;

import com.alibaba.fastjson.JSONArray;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.net.URI;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Scrapier {
    private static String Url_="https://data.lacity.org/resource/63jg-8b9z.json";
    private static String[] keysindex={"victdescent","crmcd","part1to2","weaponusedcd","daterptd","crmcd1","crmcd2","crossstreet","crmcd3","victage","areaname","rptdistno","status","mocodes","lat","crmcd4","premiscd","dateocc","location","victsex","lon","area","premisdesc","crmcddesc","weapondesc","dr_no","timeocc","statusdesc",};




//    private static  CloseableHttpClient httpClient= HttpClients.createDefault();

    /**
     *
     * @param single jsonobject数据
     * @return Csvdata数组
     */
    public static ArrayList<String> getCleareddata(JSONObject single)
    {
        Map<String,String>storemap_= new HashMap<>();
        for (Map.Entry<String,Object>pair:single.entrySet())
        {
            String newkey=pair.getKey().replace("_","");
//            System.out.println(newkey);
            if(newkey.equals("part12"))
            {
                newkey="part1to2";
            }
            else if (newkey.equals("drno"))
            {
                newkey="dr_no";
            }

            storemap_.put(newkey,String.valueOf(pair.getValue()));

        }
//        System.out.println(storemap_);


        ArrayList<String> line=new ArrayList<>();
        for (String keycol :keysindex)
        {
            String storeval=storemap_.get(keycol);
            if(storeval!=null)
                if(keycol.equals("daterptd") || keycol.equals("dateocc"))
                {
                    storeval=storeval.split("T")[0].replace("-","/");
                }
                else if(keycol.equals("timeocc") || keycol.equals("dateocc"))
                {
                    storeval=storeval.substring(0,2)+":"+storeval.substring(2,4);
                }
                else if(keycol.contains("desc"))
                {
                    storeval=storeval.replace(",","#");
                }
            line.add(storeval);
        }

        return  line;
    }
    static  String toCSVline(ArrayList<String>data)
    {
        StringBuilder builder=new StringBuilder();
        for (String item :data)
        {
            if(item==null)
            {

            }
            else
            {
                builder.append(item);
            }
            builder.append(",");
        }
        builder.deleteCharAt(builder.length()-1);
       return   builder.toString();
    }

    /**
     *
     * @param maxline csv最大行数
     * @return scrapydata 爬取的数据
     * @throws Exception
     */
    static String scrapyOneShot(int maxline) throws Exception
    {
        CloseableHttpClient httpClient= HttpClients.createDefault();

//        try {

        HttpGet request = new HttpGet(Url_);

        // add request headers
//            request.addHeader("custom-key", "mkyong");
        request.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.49");
//            request.addHeader(HttpHeaders.ACCEPT,"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//            request.addHeader(HttpHeaders.HOST,"gbfs.citibikenyc.com");
//            request.addHeader(HttpHeaders.);

        CloseableHttpResponse response = httpClient.execute(request);


        // Get HttpResponse Status
        System.out.println(response.getProtocolVersion());              // HTTP/1.1
        System.out.println(response.getStatusLine().getStatusCode());   // 200
        System.out.println(response.getStatusLine().getReasonPhrase()); // OK
        System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK


        StringBuilder builder=new StringBuilder();
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // return it as a String
            String result = EntityUtils.toString(entity);
//                System.out.println(result);
            JSONArray array_= (JSONArray) JSON.parse(result);

            for (Iterator<Object> iterator = array_.iterator(); iterator.hasNext(); ) {
                JSONObject next = (JSONObject) iterator.next();
//                System.out.println();
                builder.append(toCSVline(getCleareddata(next)));
                builder.append("\n");
                if(maxline==0)
                {
                    break;
                }
                maxline--;
            }

        }
        response.close();

        return builder.toString();
    }

    /**
     *
     * @param data 爬取的数据
     * @param fs hadoopfs对象
     * @return  hdfs保存的数据路径
     * @throws Exception
     */
    public static String savetoHdfs(String data, FileSystem fs) throws Exception
    {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH_mm_ss");
        String timestamp_ = sdf.format(timestamp);
        String pathstring=String.format("/scrapydatus/scrapy_%s.csv",timestamp_);
        //debug timestamp
        System.out.println(pathstring);
        FSDataOutputStream outStream = fs.create(new Path(pathstring));
        outStream.writeChars(data);
        outStream.close();

        return pathstring;
    }
    /**
     * remember to close fs
     */
    public static FileSystem createnewDefaultFS() throws Exception
    {
        Configuration conf = new Configuration();


        conf.set("fs.defaultFS", String.format("hdfs://%s:9000", Hdfstest.hadoop1url));
        /**
         * 参数优先级： 1、客户端代码中设置的值 2、classpath下的用户自定义配置文件 3、然后是服务器的默认配置
         */
        conf.set("dfs.replication", "3");


        FileSystem fs = FileSystem.get(new URI(String.format("hdfs://%s:9000", Hdfstest.hadoop1url)),conf);

        return fs;
    }

    /**
     * 生成导入到hive的sql语句;
     * @param path hdfs数据保存的路径
     * @return 生成导入到hive的sql语句; 比如load data inpath '${path}' into table crimedatabase.crimedata;
     */
    public static String generateSqlLoadToHivedatabase(String path)
    {
//       StringBuilder builder=new StringBuilder();
       return String.format("load data inpath '%s' into table crimedatabase.crimedata",path);
    }

}

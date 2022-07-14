package cn.tedu.nybike.myBatis;

import cn.tedu.nybike.mapper.StationInfoMapper;
import cn.tedu.nybike.pojo.StationInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 使用MyBatis操作station_info表
 */
@SpringBootTest
@MapperScan("cn.tedu.nybike.mapper")
public class Demo03 {
    @Autowired(required = false)
    private StationInfoMapper mapper;

    List<StationInfo> list = new ArrayList<>();

    public void readFile() throws IOException {
        String fileStr = FileUtils.readFileToString(
                new File("src/main/resources/data/info.json"),
                "utf-8");
        // JSON字符串的解析
        JSONObject jsonObject = JSON.parseObject(fileStr);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray stations = data.getJSONArray("stations");
        for (Object station : stations) {
            JSONObject stat = (JSONObject) station;
            String id = stat.getString("station_id");
            String shortName = stat.getString("short_name");
            String name = stat.getString("name");
            String lon = stat.getString("lon");
            String lat = stat.getString("lat");

            StationInfo info = new StationInfo(id, name, shortName, lon, lat);
            list.add(info);
        }
    }

    @Test
    public void test1() throws IOException {
        readFile();
        long time1 = new Date().getTime();
        for (StationInfo stationInfo : list) {
            mapper.insertInfo(stationInfo);
        }
        long time2 = new Date().getTime();
        System.err.println("总共花费："+(time2-time1));
    }

    @Test
    public void test2() throws IOException {
        readFile();
        long time1 = new Date().getTime();
        mapper.insertInfos(list);
        long time2 = new Date().getTime();
        System.err.println("总共花费："+(time2-time1));
    }

    @Test
    public void test3(){
        List<StationInfo> list = mapper.queryAll();
        System.out.println(list);
    }

}

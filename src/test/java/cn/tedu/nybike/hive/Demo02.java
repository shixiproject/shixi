package cn.tedu.nybike.hive;

import cn.tedu.nybike.mapper.mysql.StuMapper;
import cn.tedu.nybike.pojo.Stu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Demo02 {

    @Autowired(required = false)
    private StuMapper stuMapper;

    @Test
    void test1(){
        List<Stu> stus = stuMapper.listStus();
        System.out.println(stus);
    }
}

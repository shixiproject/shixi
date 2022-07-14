package cn.tedu.nybike.myBatis;

import cn.tedu.nybike.mapper.StuMapper;
import cn.tedu.nybike.pojo.Stu;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * MyBatis的使用：
 *      1. 创建和数据库表对应的实体类（封装查询得到的每一条数据到一个对象中）
 *      2. 添加mybatis的依赖
 *      3. 创建mapper接口StuMapper.java,指定常用的方法
 *      4. 创建mybatis的映射文件（resources/mapper/StuMapper.xml）编写xml文件
 *      5. 在application.properties文件添加 数据库相关的配置
 *      6. 在SpringBoot启动文件中，添加扫描组件
 *
 *      如果实体类中的属性名称和表的字段名称不一致：
 *          如果类中的属性名称不能更改：
 *               如果属性较多，使用resultMap进行映射（MyBatis）
 *               如果属性较少，取别名
 */
@SpringBootTest
@MapperScan("cn.tedu.nybike.mapper")
public class Demo02 {
    @Autowired(required = false)
    private StuMapper mapper;

    @Test
    public void test1(){
        List<Stu> list = mapper.queryAllStus();
        System.out.println(list);
    }

    @Test
    public void test3(){
        Stu stu = mapper.queryStuById(1);
        System.out.println(stu);
    }

    @Test
    public void test2(){
        List<Stu> stus = mapper.querAllStu2();
        System.out.println(stus);
    }

}

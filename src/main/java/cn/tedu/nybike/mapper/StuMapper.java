package cn.tedu.nybike.mapper;

import cn.tedu.nybike.pojo.Stu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作Stu表相关的内容
 */
public interface StuMapper {

    List<Stu> queryAllStus();

    @Select("select id, name, age s_age from stu")
    List<Stu> querAllStu2();

    @Select("select * from stu where id=#{id}")
    Stu queryStuById(int id);
}

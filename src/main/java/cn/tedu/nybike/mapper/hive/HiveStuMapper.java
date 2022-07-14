package cn.tedu.nybike.mapper.hive;

import cn.tedu.nybike.pojo.PieVO;
import cn.tedu.nybike.pojo.Stu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HiveStuMapper {
    @Select("select * from stu")
    List<Stu> listHiveStus();

    @Select("select sex,count(1) from stu group by sex")
    List<PieVO> listPies();

}

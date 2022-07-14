package cn.tedu.nybike.mapper;

import cn.tedu.nybike.pojo.StationInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StationInfoMapper {
    /**
     * 查询所有站点信息数据
     */
    @Select("select * from station_info")
    List<StationInfo> queryAll();

    /**
     * 单条数据插入
     */
    void insertInfo(StationInfo info);

    /**
     * 批量数据插入
     */
    void insertInfos(List<StationInfo> list);

}

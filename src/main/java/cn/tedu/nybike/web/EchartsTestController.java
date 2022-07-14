package cn.tedu.nybike.web;

import cn.tedu.nybike.mapper.hive.HiveStuMapper;
import cn.tedu.nybike.pojo.PieVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/echarts")
public class EchartsTestController {

    @Autowired(required = false)
    private HiveStuMapper hiveStuMapper;

    @RequestMapping("/pie")
    public String testPie(){
        List<PieVO> list =hiveStuMapper.listPies();
        return JSON.toJSONString(list);
    }
}

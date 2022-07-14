package cn.tedu.nybike.web;

import cn.tedu.nybike.mapper.StationInfoMapper;
import cn.tedu.nybike.pojo.StationInfo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用来和前台进行数据交互
 *      接受前台的请求、向前台返回响应数据
 */
@RestController     // 标识当前类为处理请求的类；返回的数据为String
//@RequestMapping("/stations")  // 在方法上也可以使用 @RequestMapping注解，添加之后，方法的具体访问URI
// 就变为了 类上的URI拼接方法上的URI
public class StationController {
    @Autowired(required = false)
    private StationInfoMapper mapper;

    @Autowired
    private ServletContext sc;

    @RequestMapping("/infos")           // localhost:8080/nybike/infos
    public String getInfos(){
        List<StationInfo> list = mapper.queryAll();
        // 先将该Java对象转变为JSON字符串
        String jsonStr = JSON.toJSONString(list);
        return jsonStr;
    }

    @RequestMapping("/status")
    public String getStatus(){
        // 先从ServletContext对象中获取
        // 根据key从作用域中获取对应的数据
        String statusStr = sc.getAttribute("STATION_STATUS").toString();

        // 如果ServletContext中没有该数据，此时需要从大数据平台获取

        return statusStr;
    }


/*    @RequestMapping("/status")
    public String getStatus(HttpServletRequest request){
        // 先通过request对象获取 ServletContext
        ServletContext sc = request.getServletContext()
    }*/

}

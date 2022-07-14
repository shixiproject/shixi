package cn.tedu.nybike.conf;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 周期性的任务：从共享单车官网获取站点状态数据
 *      该任务在项目启动的时候，就开始执行。
 *      所以该任务应该交由Spring容器管理。
 */
@Component
public class GetStationStatus {
    @Autowired
    private FileSystem fs;

    /**
     * ServletContext对象表示当前项目的唯一对象。当在启动项目的时候，会自动创建该对象
     *      （当使用SpringBoot项目的时候，Spring容器会自动管理该对象）
     */
    @Autowired
    private ServletContext sc;

    // 通过ScheduledExecutorService实现周期性的任务。
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    String url = "https://gbfs.citibikenyc.com/gbfs/en/station_status.json";

    public GetStationStatus(){
//        scheduleAtFixedRate() 该方法需要四个参数：
//                  1. 周期性任务(Runnable--线程) 2. 第一次启动任务时，延迟时间
//                  3. 周期性任务的间隔时间 4. 时间单位
        MyRunnable runnable = new MyRunnable();
        service.scheduleAtFixedRate(runnable,       // 周期性任务
                0,                                  // 第一次启动任务的延迟时间
                10,                                 // 任务的间隔时间
                TimeUnit.SECONDS);                  // 时间的单位
    }

    class MyRunnable implements Runnable{
        private RestTemplate template;

        public MyRunnable(){
            // 创建工厂对象
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            // 设置连接超时时间和数据读取时间
            factory.setConnectTimeout(5000);
            factory.setReadTimeout(5000);
            // 初始化 RestTemplate
            template = new RestTemplate(factory);
        }

        @Override
        public void run() {
            // 当前URL的请求，允许失败4次
            for(int i=0; i<5; i++) {
                String statusStr = template.getForObject(url, String.class);
                if(statusStr != null){
                    System.out.println(statusStr.length());
                    // 将查询得到数据保存在文件中：文件名称 当前的时间（07/09/11：50：xx）
                    // 使用 fs 对象将保存的文件上传HDFS   /stations/status/

                    // 将查询得到的数据保存在ServletContext对象（充当缓存的效果）
                    // 作用域里保存数据：（key，value）
                    sc.setAttribute("STATION_STATUS", statusStr);
                    break;
                }
            }

        }
    }
}

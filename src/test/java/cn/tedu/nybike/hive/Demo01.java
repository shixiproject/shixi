package cn.tedu.nybike.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 1. 加载依赖（导入驱动包）
 * 2. 加载驱动类
 * 3. 创建连接对象
 * 4. 创建传输对象
 * 5. 执行SQL，返回结果集
 * 6. 遍历结果集
 * 7. 关闭资源
 */
public class Demo01 {
    public static void main(String[] args) throws Exception {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        String url = "jdbc:hive2://192.168.137.100:10000/test";
        String user = "root";
        String pwd = "root";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        String hql = "select * from stu";
        PreparedStatement ps = conn.prepareStatement(hql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(2));
        }
    }
}

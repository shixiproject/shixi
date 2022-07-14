package cn.tedu.nybike.myBatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JDBC的使用：
 *      1. 导入依赖jar包
 *      2. 加载驱动类
 *      3. 创建连接对象
 *      4. 创建传输对象（SQL的执行器）：将SQL语句传输给SQL服务器
 *      5. 执行SQL语句
 *          5-1：如果执行的是查询语句，返回结果集对象
 *          5-2：如果执行的是增加、删除、修改操作，可以不管返回值
 *      6. 关闭资源
 */
public class Demo01 {
    public static void main(String[] args) throws Exception {
        String idpar = "900000 or 1=1";

        // 加载驱动
        // 如果是MySQL 8.x ，使用的驱动：com.mysql.cj.jdbc.Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // url格式：   jdbc:mysql://IP:PORT/数据库名称?参数1=值1&参数2=值2
        // 当MySQL安装在本机，使用的端口号为默认端口号3306的时候，可以省略IP和端口号
        String url = "jdbc:mysql:///nybike";
        String user = "root";
        String password = "Cwy011222";
        Connection conn = DriverManager.getConnection(url, user, password);

        String sql = "select id, name, age as s_age from stu where id=? and user=?";  // id  name   s_age
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setString(2, "xxx");

        ResultSet rs = ps.executeQuery();
        while(rs.next()){ // rs.next() 有两个作用：判断rs对象中是否有数据；获取下一条数据
            int id = rs.getInt(1);
            String name = rs.getString("name");
            String age = rs.getString("s_age");
            System.out.println(id +","+ name +","+ age);
            // 下一步，将查询得到的数据封装到Java对象中
        }
    }
}

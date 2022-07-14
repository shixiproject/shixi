package org.example.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hivetest
{
    static String hadoop1url="10.1.75.45";
    @Test
    public  void hivetest() throws  Exception
    {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        Connection con = DriverManager.getConnection(String.format("jdbc:hive2://%s:10000/test",hadoop1url),"root","");
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM stu");

        System.out.println("query 1:");
        while (res.next())
        {
            System.out.println(String.format("name %s;sex %s",res.getString("name"),res.getString("sex")));
        }
        System.out.println("query mapreduce 2:");
        stmt = con.createStatement();
        res = stmt.executeQuery("select sex,name,count(1)as c from stu group by sex,name");
//        res = stmt.executeQuery("SELECT * FROM stu");
        while (res.next())
        {
            System.out.println(String.format("name %s;sex %s",res.getString("name"),res.getString("sex")));
        }

        System.out.println("end----------------");
    }
}

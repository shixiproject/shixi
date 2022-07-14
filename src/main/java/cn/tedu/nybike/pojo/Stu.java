package cn.tedu.nybike.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 和数据库中stu表一一对应的实体类
 *      封装stu表中的数据
 *
 *  实体类中，添加的属性通常都为 private
 *      给该实体类添加对应的getter、setter、toString、构造
 */
@Data
@AllArgsConstructor     // 全参构造
@NoArgsConstructor      // 无参构造
public class Stu {
    private int id;
    private String name;
    private int s_age;
}

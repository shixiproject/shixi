package cn.tedu.nybike.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//前台显示饼图时需要的数据格式
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieVO {
    private String name;
    private int value;
}

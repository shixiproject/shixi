package cn.tedu.nybike.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationInfo {
    private String stationid;
    private String name;
    private String shortName;
    private String lon;
    private String lat;
}

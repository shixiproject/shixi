package cn.tedu.nybike.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 用来读取 application.properties中指定的数据源内容
 */
@Data
@ConfigurationProperties(prefix = DataSourceProperties.DS, ignoreUnknownFields = false)
public class DataSourceProperties {
    static final String DS = "spring.datasource.dbs";

    private Map<String, String> mysql;

    private Map<String, String> hive;
}

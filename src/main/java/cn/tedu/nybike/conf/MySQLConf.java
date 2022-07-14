package cn.tedu.nybike.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * MySQL数据源配置文件
 *      1. 创建数据源--DataSources
 *      2. 创建SqlSessionFactory
 *      3. 创建SqlSessionTemplate（SqlSession）
 */
@Configuration
@MapperScan("cn.tedu.nybike.mapper.mysql")
@EnableConfigurationProperties({DataSourceProperties.class})
public class MySQLConf {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Primary    // 指定当前的数据源为主数据源
    @Bean("mysqlDataSource")    // 给bean指定名称，并交由Spring容器管理
    public DataSource mysqlDataSource(){
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(dataSourceProperties.getMysql().get("driverClass"));
        dataSource.setUrl(dataSourceProperties.getMysql().get("url"));
        dataSource.setUsername(dataSourceProperties.getMysql().get("username"));
        dataSource.setPassword(dataSourceProperties.getMysql().get("password"));

        return dataSource;
    }

    /**
     *  @Qualifier("mysqlDataSource") DataSource dataSource
     *      指定当前方法中必须传入 对象名称mysqlDataSource 的DataSource对象
     */
    @Primary
    @Bean("mysqlFactory")
    public SqlSessionFactory mysqlFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        // SqlSessionFactoryBean 用来创建SqlSessionFactory对象的
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // 指定数据源
        bean.setDataSource(dataSource);
        // 指定执行的 xml 文件路径
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/mysql/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean("mysqlSession")
    public SqlSessionTemplate mysqlSession(@Qualifier("mysqlFactory") SqlSessionFactory factory){
        SqlSessionTemplate template = new SqlSessionTemplate(factory);
        return template;
    }
}

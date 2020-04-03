package com.sunmnet.bigdata.task.config.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages ="com.sunmnet.bigdata.task.dao.slave",sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class DruidDataSourceSlave {

    @Value("${spring.datasource.slave.url}")
    private String dbUrl;

    @Value("${spring.datasource.slave.username}")
    private String username;

    @Value("${spring.datasource.slave.password}")
    private String password;

    @Value("${spring.datasource.slave.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource slaveDataSource(){
        DataSource druidDataSource = DataSourceBuilder.create()
                .username(username)
                .password(password)
                .url(dbUrl)
                .type(DruidDataSource.class)
                .driverClassName(driverClassName).build();
        return druidDataSource;
    }

    /**
     * 返回data2数据库的会话工厂
     * @param ds
     * @return
     * @throws Exception
     */
    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource ds) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否分页合理化
        properties.setProperty("reasonable", "false");
        interceptor.setProperties(properties);

        bean.setPlugins(new Interceptor[] {interceptor});
        bean.setDataSource(ds);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/slave/*.xml"));
        return bean.getObject();
    }

    /**
     * 返回data1数据库的会话模板
     * @param sessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "data2SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sessionFactory) throws  Exception{
        return  new SqlSessionTemplate(sessionFactory);
    }

    /**
     * 返回data1数据库的事务
     * @param ds
     * @return
     */
    @Bean(name = "data2TransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("slaveDataSource") DataSource ds){
        return new DataSourceTransactionManager(ds);
    }
}

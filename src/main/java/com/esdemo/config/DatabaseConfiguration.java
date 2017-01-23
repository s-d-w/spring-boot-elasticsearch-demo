package com.esdemo.config;

import java.sql.SQLException;
import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.esdemo.repository")
@EnableTransactionManagement
@EnableElasticsearchRepositories("com.esdemo.repository.search")
public class DatabaseConfiguration {
    
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TCPServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers");
    }
    
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:liquibase/master.xml");
        liquibase.setDropFirst(false);
        return liquibase;
    }    
}

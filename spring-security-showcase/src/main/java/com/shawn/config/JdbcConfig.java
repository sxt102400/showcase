package com.shawn.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@PropertySource("classpath:jdbc.properties")
@Configuration
@EnableJdbcRepositories
public class JdbcConfig extends AbstractJdbcConfiguration {
    private static Logger logger= LoggerFactory.getLogger(JdbcConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {

        logger.info("url {}",env.getProperty("spring.database.url"));
        logger.info("driverClassName {}",env.getProperty("spring.database.driverClassName"));
        logger.info("username {}",env.getProperty("spring.database.username"));
        logger.info("password {}",env.getProperty("spring.database.password"));
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.database.url"));
        dataSource.setDriverClassName(env.getProperty("spring.database.driverClassName"));
        dataSource.setUsername(env.getProperty("spring.database.username"));
        dataSource.setPassword(env.getProperty("spring.database.password"));
        return dataSource;
    }

    @Bean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

package com.siemens.customerapi.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(VaultConfiguration.class)
public class DbConfiguration {

    @Value("${db_url_docker}")
    private String dbUrl;
    @Value("${db_driver}")
    private String driver;

    private DataSourceBuilder dataSourceBuilder;
    private final VaultConfiguration vaultConfiguration;

    public DbConfiguration (VaultConfiguration vaultConfig)
    {

        this.vaultConfiguration=vaultConfig;
    }


    @Bean
    public DataSource getDataSource()
    {
        System.out.println(dbUrl);
        System.out.println(driver);

        System.out.println("Entering Given Env.....");
        System.out.println("User Name..."+vaultConfiguration.getMysqlusername());
        System.out.println("Password..."+vaultConfiguration.getPassword());
        //System.out.println("User Name..."+vaultConfiguration.getUsername1());
        //System.out.println("Password..."+vaultConfiguration.getPassword1());
        dataSourceBuilder=DataSourceBuilder.create();
        dataSourceBuilder.url(dbUrl);
        dataSourceBuilder.username(vaultConfiguration.getMysqlusername());
        dataSourceBuilder.password(vaultConfiguration.getPassword());
        dataSourceBuilder.driverClassName(driver);
        return dataSourceBuilder.build();

    }

}

package com.curtain.readwriteseparation;

import com.dangdang.ddframe.rdb.sharding.api.MasterSlaveDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.strategy.slave.MasterSlaveLoadBalanceStrategyType;
import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 主数据库shardingrw0，shardingrw1、shardingrw2作为从库
 * 主库写，从库读。（只在查询上做了，mysql上没做主从配置 数据无法同步）
 * 多库读 sharing只支持两种：随机、轮询
 * 当前使用轮询
 * @author Curtain
 * @date 2019/4/23 9:43
 */
@Configuration
public class DataSourceConfig {
    @Autowired
    private Shardingrw0Config shardingrw0Config;

    @Autowired
    private Shardingrw1Config shardingrw1Config;

    @Autowired
    private Shardingrw2Config shardingrw2Config;

    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {

        Map<String, DataSource> slaveDataSource = new HashMap<>();

        slaveDataSource.put(shardingrw1Config.getDatabaseName(), shardingrw1Config.createDataSource());
        slaveDataSource.put(shardingrw2Config.getDatabaseName(), shardingrw2Config.createDataSource());

        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource("masterSlave", shardingrw0Config.getDatabaseName()
                , shardingrw0Config.createDataSource(), slaveDataSource, MasterSlaveLoadBalanceStrategyType.getDefaultStrategyType());

        return dataSource;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }
}

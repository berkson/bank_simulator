package com.berkson.bank_simulator.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created By : Berkson Ximenes
 * Date : 06/12/2024
 **/

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.berkson.bank_simulator.repository"},
        entityManagerFactoryRef = "bankDbManagerFactory")
public class DbConfig {
    @Value("${dbuser}")
    private String user;
    @Value("${dbsecret}")
    private String pass;
    @Value("${dburl}")
    private String url;
    @Value("${dbdriver}")
    private String driver;

    @Bean(name = "bankDb")
    @Primary
    public DataSource bankDataSource() {

        return DataSourceBuilder
                .create()
                .username(this.user).password(this.pass)
                .url(this.url).driverClassName(this.driver).build();
    }

    @Primary
    @Bean(name = "bankDbManagerFactory")
    public LocalContainerEntityManagerFactoryBean bankDbManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("bankDb") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.berkson.bank_simulator.domain").persistenceUnit("bank").build();
    }

    @Primary
    @Bean(name = "bankDbTransactionManager")
    public PlatformTransactionManager dbSaticTransactionManager(
            @Qualifier("bankDbManagerFactory") EntityManagerFactory locargaEntityManagerFactory) {
        return new JpaTransactionManager(locargaEntityManagerFactory);
    }
}

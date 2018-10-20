package io.khasang.ba.config;

import io.khasang.ba.dao.CatDao;
import io.khasang.ba.dao.RoleDao;
import io.khasang.ba.dao.UserDao;
import io.khasang.ba.dao.impl.CatDaoImpl;
import io.khasang.ba.dao.impl.RoleDaoImpl;
import io.khasang.ba.dao.impl.UserDaoImpl;
import io.khasang.ba.entity.Cat;
import io.khasang.ba.entity.Role;
import io.khasang.ba.entity.User;
import io.khasang.ba.service.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
@PropertySource(value = "classpath:util.properties")
@PropertySource(value = "classpath:auth.properties")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.postgresql.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.postgresql.user"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource());
        jdbcDao.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        jdbcDao.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return jdbcDao;
    }

    @Bean
    public CreateTable createTable() {
        return new CreateTable(jdbcTemplate());
    }

    @Bean
    public CatDao catDao() {
        return new CatDaoImpl(Cat.class);
    }

    @Bean
    public RoleDao roleDao() {
        return new RoleDaoImpl(Role.class);
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl(User.class);
    }
}

package ro.example.multi_env_jdbc.config;


import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Profile("prod")
    @ConditionalOnExpression("${app.datasource.useCloudDb:false} == true")
    public DataSource cloudDataSource(DatabaseProperties props) {
        System.out.println(">>> Using CLOUD DataSource");
        return DataSourceBuilder.create()
                .url(props.toJdbcUrl())
                .username(props.getUsername())
                .password(props.getPassword())
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean
    @Profile("dev")
    @ConditionalOnExpression("${app.datasource.useCloudDb:false} == false")
    public DataSource defaultDataSource(DatabaseProperties props) {
        System.out.println(">>> Using DEFAULT DataSource");
        return DataSourceBuilder.create()
                .url(props.toJdbcUrl())
                .username(props.getUsername())
                .password(props.getPassword())
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}

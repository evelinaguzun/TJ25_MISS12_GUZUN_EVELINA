package ro.example.multi_env_jdbc.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import ro.example.multienvjdbc.config.DatabaseProperties;

@Component
public class DatabaseInfoContributor implements InfoContributor {
    private final DatabaseProperties props;

    public DatabaseInfoContributor(DatabaseProperties props) {
        this.props = props;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("database",
                java.util.Map.of(
                        "host", props.getHost(),
                        "port", props.getPort(),
                        "name", props.getName(),
                        "username", props.getUsername(),
                        "useCloudDb", props.isUseCloudDb()
                )
        );
    }
}

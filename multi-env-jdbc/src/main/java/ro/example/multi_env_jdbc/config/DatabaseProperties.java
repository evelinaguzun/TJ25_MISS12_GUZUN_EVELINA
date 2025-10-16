package ro.example.multi_env_jdbc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.datasource")
public class DatabaseProperties {
    private String host;
    private int port;
    private String name;
    private String username;
    private String password;
    private boolean useCloudDb;

    // Getters and Setters
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }
    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isUseCloudDb() { return useCloudDb; }
    public void setUseCloudDb(boolean useCloudDb) { this.useCloudDb = useCloudDb; }

    public String toJdbcUrl() {
        return String.format("jdbc:postgresql://%s:%d/%s", host, port, name);
    }
}

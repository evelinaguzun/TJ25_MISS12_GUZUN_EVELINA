package ro.example.multi_env_jdbc.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class DbPrinterRunner implements CommandLineRunner {
    private final JdbcTemplate jdbc;

    public DbPrinterRunner(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Starting DB check ===");
        try {
            List<Map<String, Object>> rows = jdbc.queryForList("SELECT key, value FROM app_info");
            for (Map<String, Object> row : rows) {
                System.out.println("Row: " + row);
            }
        } catch (Exception e) {
            System.out.println("DB read failed: " + e.getMessage());
        }
    }
}

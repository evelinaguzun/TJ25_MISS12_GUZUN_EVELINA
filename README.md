# Spring Boot app-Lab 2-Homework

Această aplicație **Spring Boot** demonstrează cum se poate configura o conexiune la baze de date diferite (de exemplu *development* și *production*) folosind **Spring Profiles**.  
Aplicația detectează automat profilul activ și se conectează la baza de date potrivită, apoi afișează datele dintr-un tabel la pornire.

---

## Structura proiectului

- `application.yml` – configurația principală, unde profilul activ este setat implicit (`dev`).
- `application-dev.yml` – conține datele de conectare la baza de date de dezvoltare.
- `application-prod.yml` – conține datele pentru baza de date de producție.
- `DataSourceConfig.java` – creează DataSource-ul potrivit pe baza profilului și a expresiei `@ConditionalOnExpression`.
- `DbPrinterRunner.java` – un `CommandLineRunner` care, la pornire, face un query SQL și afișează valorile din tabelul `app_info`.
- `DatabaseProperties.java` – leagă proprietățile din YAML la un obiect Java.
- `docker-compose.yml` – rulează un container PostgreSQL pentru baza de date `dev`.

---

## Cum funcționează
Aplicația folosește **Spring Profiles** pentru a alege automat ce configurație de bază de date să folosească.

### `application-dev.yml`
```yaml
spring:
  config:
    activate:
      on-profile: dev

app:
  datasource:
    host: localhost
    port: 5432
    name: devdb
    username: devuser
    password: devpass
    useCloudDb: false
```
### `application-prod.yml`
```yaml
spring:
  config:
    activate:
      on-profile: prod

app:
  datasource:
    host: localhost
    port: 5432
    name: proddb
    username: produser
    password: prodpass
    useCloudDb: true
```
## Logica de selecție – DataSourceConfig.java
```java
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
```
Dacă profilul activ este **prod** și **useCloudDb=true**, aplicația va folosi un DataSource „cloud”.
Altfel, folosește conexiunea locală definită în fișierul **application-dev.yml**.

## Demonstrarea conexiunii
La pornire, aplicația rulează **DbPrinterRunner**, care verifică dacă baza de date este accesibilă și citește date din tabelul **app_info**:
```java
@Override
public void run(String... args) {
    System.out.println("=== Starting DB check ===");
    List<Map<String, Object>> rows = jdbc.queryForList("SELECT key, value FROM app_info");
    for (Map<String, Object> row : rows) {
        System.out.println("Row: " + row);
    }
}
```
Dacă aplicația se conectează corect, în consolă apare ceva de genul:

<img width="1920" height="1027" alt="image" src="https://github.com/user-attachments/assets/c0a3335c-5e79-489b-9bd4-b6f9a0c58a63" />

## Schimbarea mediului
Profilul activ se poate schimba din linia de comandă sau din IntelliJ, la **Program arguments**:

```ini
--spring.profiles.active=prod --app.datasource.username=cliuser
```
Aceasta demonstrează și faptul că argumentele din linia de comandă suprascriu valorile din YAML, conform cerinței.

Dacă accesăm http://localhost:8080/actuator/info ar trebui să vedem:

<img width="1920" height="566" alt="image" src="https://github.com/user-attachments/assets/23c0ebf4-2833-459a-9740-7c99e503ecc7" />

<img width="1920" height="557" alt="image" src="https://github.com/user-attachments/assets/2b9cd2f7-9870-48e2-b266-492b0408ef00" />

## Docker
Pentru mediul de dezvoltare, am folosit un container PostgreSQL pornit prin **docker-compose**:

```yaml
services:
  postgres:
    image: postgres:15
    environment:
      POSTGRES_USER: devuser
      POSTGRES_PASSWORD: devpass
      POSTGRES_DB: devdb
    ports:
      - "5432:5432"
```
P.S. Docker funcționează ok, dar pornește cam lent și uneori întârzie puțin aplicația.  
Probabil că ar fi mai practic să mă conectez direct la o instanță locală de PostgreSQL sau să folosesc un serviciu cloud gen Supabase.

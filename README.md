# Lab 3-Compulsory
Acest proiect Spring Boot demonstrează principalele tipuri de **Dependency Injection** și ordinea în care Spring realizează injecțiile.

---

## Tipuri de injecție demonstrate
1. **Constructor Injection** – obiectele sunt create și dependențele sunt injectate prin constructor.  
2. **Field Injection** – câmpurile marcate cu `@Autowired` sunt completate automat.  
3. **Setter & Method Injection** – dependențele sunt injectate prin metode adnotate cu `@Autowired`.

---

### 1. Constructor Injection – `ServiceA`

Spring creează instanța lui **ServiceA** prima, folosind constructorul.

```java
@Component
public class ServiceA {
    public ServiceA() {
        System.out.println("1️⃣ ServiceA: constructor called (created first)");
    }

    public String getName() {
        return "ServiceA";
    }
}
```

### 2. Field Injection – `ServiceB`

Dependența **ServiceA** este injectată automat direct în câmpul **serviceA**.

```java
@Component
public class ServiceB {

    @Autowired
    private ServiceA serviceA; // Field injection

    public ServiceB() {
        System.out.println("2️⃣ ServiceB: constructor called");
    }

    public void print() {
        System.out.println("   ServiceB: using " + serviceA.getName());
    }
}
```
### 3. Setter & Method Injection – `ServiceC`

După ce obiectul este creat, Spring apelează automat metodele marcate cu **@Autowired**.

```java
@Component
public class ServiceC {

    private ServiceB serviceB;

    public ServiceC() {
        System.out.println("3️⃣ ServiceC: constructor called");
    }

    @Autowired
    public void setServiceB(ServiceB serviceB) {
        System.out.println("➡️ ServiceC: setter injection called");
        this.serviceB = serviceB;
    }

    @Autowired
    public void anotherMethod(ServiceA serviceA) {
        System.out.println("➡️ ServiceC: method injection called with " + serviceA.getName());
    }

    public void action() {
        System.out.println("   ServiceC: action() running...");
        serviceB.print();
    }
}
```
---

## Rularea aplicației - `DemoRunner.java`

```java
@Component
public class DemoRunner implements CommandLineRunner {

    private final ServiceC serviceC;

    public DemoRunner(ServiceC serviceC) {
        System.out.println("💉 DemoRunner: constructor injection");
        this.serviceC = serviceC;
    }

    @Override
    public void run(String... args) {
        System.out.println("🚀 Application started. Let's check dependency order:");
        serviceC.action();
    }
}
```
---
## Rezultatul în consolă

<img width="1920" height="1029" alt="image" src="https://github.com/user-attachments/assets/e36caf8b-9150-4c18-85ff-d5a7717ff494" />






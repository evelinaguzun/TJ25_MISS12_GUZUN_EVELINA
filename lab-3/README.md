# Laborator 3 – Tehnologii Java
## Dependency Injection și Cross-Cutting Concerns

Acest proiect Spring Boot demonstrează principalele tipuri de **Dependency Injection** și ordinea în care Spring realizează injecțiile.

---

### Tipuri de injecție demonstrate
1. **Constructor Injection** – obiectele sunt create și dependențele sunt injectate prin constructor.
2. **Field Injection** – câmpurile marcate cu `@Autowired` sunt completate automat.
3. **Setter & Method Injection** – dependențele sunt injectate prin metode adnotate cu `@Autowired`.

Ordinea de execuție demonstrată:

Constructor → Field → Setter & Method
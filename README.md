# Lab 5-Compulsory + Homework (extindere Lab4)

## Homework 

## Funcționalități implementate:
- Extinderea modelului de domeniu prin entitatea StudentPreference (legătură între Student și Course);
- Implementarea completă a operațiilor CRUD pentru StudentPreference;
- Utilizarea DTO-urilor și a Bean Validation pentru validarea datelor de intrare;
- Implementarea unei excepții personalizate (ResourceNotFoundException) și gestionarea globală în GlobalExceptionHandler;
- Adăugarea de conditional requests folosind ETag și antetul If-None-Match;
- Suport pentru content negotiation (JSON/XML);
- Documentarea API-ului cu Springdoc OpenAPI (Swagger UI) pentru testare vizuală a endpoint-urilor.
---
### Afișarea preferințelor (GET)
<img width="1920" height="589" alt="image" src="https://github.com/user-attachments/assets/5c10f83f-ac0a-40c5-befa-30925bc67684" />
La prima rulare, lista este goală, deoarece nu a fost adăugată încă nicio preferință în baza de date.

### Adăugarea unei preferințe noi (POST)
<img width="1920" height="1027" alt="image" src="https://github.com/user-attachments/assets/eb361bd6-0b22-45f7-9778-0cc2a88e711b" />
Aici, studentul cu ID 2 a ales cursul cu ID 3, având ordinea de preferință 1.

### Verificarea inserării în baza de date (pgAdmin)
<img width="1920" height="789" alt="image" src="https://github.com/user-attachments/assets/dc218a22-8697-4c3a-aa00-01f4970febc8" />

### Actualizarea unei preferințe existente (PUT)
<img width="1920" height="724" alt="image" src="https://github.com/user-attachments/assets/91a1c9a5-7bf7-4166-bc3d-1f3fe2462415" />
<img width="1908" height="869" alt="image" src="https://github.com/user-attachments/assets/83173aae-0f5f-4c26-a936-e6f3ba115af7" />
În acest exemplu, ordinea de preferință a fost modificată de la 1 la 2.

### Ștergerea unei preferințe (DELETE)
<img width="1920" height="182" alt="image" src="https://github.com/user-attachments/assets/3dfad61d-cec7-4bae-acd1-07ee52230e7c" />
<img width="1920" height="854" alt="image" src="https://github.com/user-attachments/assets/525feab6-e36f-4490-b0f4-a41cf96ebcc9" />

### Content ngotiation (JSON/XML)
<img width="1920" height="124" alt="image" src="https://github.com/user-attachments/assets/fcf62819-05a6-4f6f-9f57-bdd6ee7114a3" />
Endpointul suportă content negotiation, același URL poate returna datele fie în format JSON, fie în XML, în funcție de antetul Accept.

### Documentarea cu Swagger 
Interfața vizuală Swagger poate fi accesată la:
http://localhost:8080/swagger-ui/index.html
<img width="1920" height="970" alt="image" src="https://github.com/user-attachments/assets/bb6d0c45-1235-4e90-9334-d1166ba58ea0" />
<img width="1920" height="975" alt="image" src="https://github.com/user-attachments/assets/cb3357c1-f1fa-4dc8-9a34-5fc506eba40d" />


Compulsory
---
## Funcționalități noi
- un nou pachet controller/
- clasa StudentController.java care expune endpoint-uri **REST**
- testarea completă a operațiilor CRUD cu **curl**
- configurare automată Spring Boot (port 8080)
- date generate automat cu Faker, ca și în Lab 4
---

## Testarea API-ului cu cURL

### Afișează toți studenții (GET)
<img width="1920" height="1029" alt="image" src="https://github.com/user-attachments/assets/259b98c9-4b64-4cb5-afb4-403071749b59" />

### Adaugă un student nou (POST)
<img width="1920" height="465" alt="image" src="https://github.com/user-attachments/assets/40500bdd-a300-48d7-9212-f281e0910a6c" />

<img width="1920" height="1032" alt="image" src="https://github.com/user-attachments/assets/d45f70a2-fdff-41e7-a437-e559173a4e70" />

### Actualizează un student existent (PUT)
<img width="1920" height="427" alt="image" src="https://github.com/user-attachments/assets/c2471a4c-50a3-4896-84de-834af5849f9d" />
<img width="1920" height="1033" alt="image" src="https://github.com/user-attachments/assets/d108bfe5-0571-4db9-a40e-580fd5ed5aa3" />

### Șterge un student (DELETE)
<img width="1920" height="135" alt="image" src="https://github.com/user-attachments/assets/aaea3bf0-9878-4bde-944c-1c23e7ab5100" />
<img width="1919" height="1029" alt="image" src="https://github.com/user-attachments/assets/c77cdf45-136e-4db8-b5e1-bca67a244d7b" />


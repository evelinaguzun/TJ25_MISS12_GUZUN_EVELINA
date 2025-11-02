# Lab 4-Compulsory

## Funcționalitate
Aplicația pornește cu un **CommandLineRunner** care:
- creează și salvează un student, instructor, pachet și curs în baza de date;  
- afișează în consolă mesajele de confirmare;  
- datele pot fi verificate ulterior în pgAdmin.

---

## Structura pe scurt
- `entity/` – clasele care mapează tabelele SQL (`Student`, `Instructor`, `Pack`, `Course`)
- `repository/` – interfețele pentru accesul la date  
- `Lab4Application.java` – conține logica de test cu `CommandLineRunner`  
- `application.properties` – setările de conexiune la PostgreSQL  

---

## Exemple

**Rezultat consolă**
<img width="1920" height="1031" alt="image" src="https://github.com/user-attachments/assets/4391ef3f-cc76-4071-91a3-90eb04789959" />

**pgAdmin:**
<img width="1916" height="1031" alt="image" src="https://github.com/user-attachments/assets/710d694d-a584-4b33-be51-0699f5eb3b72" />
<img width="1920" height="1030" alt="image" src="https://github.com/user-attachments/assets/a13c444c-5930-467c-9d8f-bd253a49bfe9" />
<img width="1920" height="1030" alt="image" src="https://github.com/user-attachments/assets/3f4423fc-d62a-4e5f-bb62-6670a42e9381" />
<img width="1920" height="1029" alt="image" src="https://github.com/user-attachments/assets/092847df-9390-477f-97a0-2409dbde8500" />

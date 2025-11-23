# Lab 6-Compulsory + Homework

## Homework
## Funcționalități noi 
- A fost integrată autentificarea și autorizarea cu JWT (JSON Web Tokens)
- Controlul accesului se realizează pe roluri (ADMIN, STUDENT, INSTRUCTOR).
- Endpointurile aplicației au fost securizate.
- Spring Boot Actuator a fost configurat pentru monitorizare.
  
## Înregistrare/login utilizator (ADMIN)
<img width="1920" height="460" alt="image" src="https://github.com/user-attachments/assets/1f5bf297-954b-435a-a72e-f6f0e5967d2f" />

## GET-cu Token (afișează toți studenții și cursurile)
<img width="1920" height="440" alt="image" src="https://github.com/user-attachments/assets/78f1da5d-b1aa-48ca-a8fe-2edf911947a0" />
<img width="1920" height="456" alt="image" src="https://github.com/user-attachments/assets/18e94b71-d00b-4d6e-ae45-7dc6d3b9e022" />

## GET-fără Token (unauthorized)
<img width="1920" height="80" alt="image" src="https://github.com/user-attachments/assets/f2670087-37e0-4a61-9422-6f6c6210b781" />

## POST (creează o preferință nouă)
<img width="1920" height="220" alt="image" src="https://github.com/user-attachments/assets/86202c9c-d2e7-49e3-a185-467dabdd97d4" />
<img width="1619" height="741" alt="image" src="https://github.com/user-attachments/assets/e047c966-9e94-4609-83c4-a0b13c24602d" />

## PUT (actualizează ordinea unei preferințe)
<img width="1917" height="213" alt="image" src="https://github.com/user-attachments/assets/0bba0756-913c-4ac6-8fac-0a806da33966" />
<img width="1615" height="705" alt="image" src="https://github.com/user-attachments/assets/ae2bd1f0-3512-4707-856b-db86338dae01" />

## Înregistrare/login utilizator (STUDENT)
<img width="1920" height="188" alt="image" src="https://github.com/user-attachments/assets/97d40a01-1b12-4a76-b770-3326c0b21b12" />

## DELETE-rol de student (unauthorized)
<img width="1920" height="305" alt="image" src="https://github.com/user-attachments/assets/f6c278cd-a102-41ca-9562-61ac3c7b9f2b" />

## Compulsory
## Funcționalități noi implementate:
- A fost adăugat Spring Security în proiect.
- Toate endpointurile API (/students, /preferences, etc.) sunt acum securizate.
- Doar endpointul /login este accesibil public fără autentificare.
- A fost creat un mock login endpoint (AuthController) care returnează un mesaj static, simulând autentificarea.
- Configurația de securitate este realizată în fișierul SecurityConfig, unde s-a definit regula de acces.

## Endpoint public (funcționează fără autentificare)
<img width="1920" height="467" alt="image" src="https://github.com/user-attachments/assets/81e64ebb-7b51-45f4-9eb9-b77f0d792452" />

## Endpoint protejat (necesită autentificare)
<img width="1920" height="780" alt="image" src="https://github.com/user-attachments/assets/0e3983ba-3105-4df2-803d-2569acbde62f" />

## Testare endpoint cu parola generată
<img width="1920" height="1025" alt="image" src="https://github.com/user-attachments/assets/b35807c0-74e7-44c3-81e5-d0b3d9942958" />
<img width="1920" height="1029" alt="image" src="https://github.com/user-attachments/assets/0858acb8-59eb-4429-887d-f4bf15f3e13c" />



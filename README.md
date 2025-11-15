# Lab 6-Compulsory

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



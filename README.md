# Tema Java EE - Lab 1

## Descriere
Această aplicație Java EE folosește un servlet pentru a direcționa utilizatorul către una dintre două pagini HTML (`page1.html` și `page2.html`), în funcție de selecția făcută într-un formular de pe pagina principală (`index.jsp`).  

De asemenea, aplicația poate fi invocată dintr-un client desktop (Python) care primește un răspuns text simplu cu valoarea parametrului selectat.

---

## Structura proiectului
- `index.jsp` – Pagina principală cu formularul  
- `page1.html` / `page2.html` – Pagini statice stilizate  
- `ControllerServlet.java` – Servletul care preia datele din formular și redirecționează către pagina corespunzătoare sau trimite răspuns text pentru client desktop  
- `web.xml` – Configurația servletului  
- `desktop-test/test_servlet.py` – Script Python care trimite o cerere HTTP GET către servlet și primește răspuns text simplu

---

## Logare request
Pentru fiecare request, servletul scrie în log:
- Metoda HTTP folosită  
- Adresa IP a clientului  
- User-Agent  
- Limba clientului  
- Valoarea parametrului selectat

---

## Testare
- Aplicația web rulează pe **Tomcat 11** în Eclipse IDE for Enterprise Developers.  
- Scriptul Python (`desktop-test/test_servlet.py`) trimite cerere GET către servlet și primește răspuns text simplu.

Exemplu răspuns din Python client:
Response status: 200
Response body: Selected page: 1
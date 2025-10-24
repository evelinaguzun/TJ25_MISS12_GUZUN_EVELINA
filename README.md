# Tema Java EE-Lab 1-Homework

Această aplicație Java EE folosește un servlet pentru a direcționa utilizatorul către una dintre două pagini HTML (`page1.html` și `page2.html`), în funcție de selecția făcută într-un formular de pe pagina principală (`index.jsp`).  

De asemenea, aplicația poate fi invocată dintr-un client desktop (Python) care primește un răspuns text simplu cu valoarea parametrului selectat.

---
## Tehnologii și unelte folosite

- **Java 17**
- **Eclipse IDE for Enterprise Java Developers**
- **Apache Tomcat 11.0.4**
- **Jakarta Servlet API (inclusă în Tomcat)**
- **Python 3 + biblioteca `requests`** (pentru testarea desktop)

---

## Structura proiectului
- `index.jsp` – Pagina principală cu formularul  
- `page1.html` / `page2.html` – Pagini statice stilizate  
- `ControllerServlet.java` – Servletul care preia datele din formular și redirecționează către pagina corespunzătoare sau trimite răspuns text pentru client desktop  
- `web.xml` – Configurația servletului  
- `desktop-test/test_servlet.py` – Script Python care trimite o cerere HTTP GET către servlet și primește răspuns text simplu

---

## Cum funcționează

1. Utilizatorul accesează **index.jsp** și selectează o pagină.
2. Formularul trimite cererea la **ControllerServlet**.
3. Servletul:
- citește parametrul **page**,
- scrie informații despre cerere în log,
- redirecționează către pagina HTML potrivită (sau trimite text simplu pentru aplicația desktop).
4. Rezultatul este afișat fie în browser, fie în terminalul Python.

---

## Logica aplicației

### `ControllerServlet.java`
```java
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Citim parametrul trimis de utilizator
    String page = request.getParameter("page");

    // Scriem informații în log
    System.out.println("HTTP Method: " + request.getMethod());
    System.out.println("Client IP: " + request.getRemoteAddr());
    System.out.println("User-Agent: " + request.getHeader("User-Agent"));
    System.out.println("Client Language: " + request.getHeader("Accept-Language"));
    System.out.println("Page parameter: " + page);

    // Verificăm dacă cererea vine din aplicație desktop (ex: Python)
    String userAgent = request.getHeader("User-Agent");
    boolean fromDesktopApp = userAgent != null && (
            userAgent.toLowerCase().contains("python") ||
            userAgent.toLowerCase().contains("java") ||
            userAgent.toLowerCase().contains("desktop")
    );

    // Răspuns diferit în funcție de sursa cererii
    if (fromDesktopApp) {
        response.setContentType("text/plain");
        response.getWriter().println("Selected page: " + page);
    } else {
        if ("1".equals(page)) {
            response.sendRedirect("page1.html");
        } else if ("2".equals(page)) {
            response.sendRedirect("page2.html");
        } else {
            response.getWriter().println("Parametru invalid!");
        }
    }
}
```
Exemplu de log din consola serverului:
```sql
HTTP Method: GET
Client IP: 0:0:0:0:0:0:0:1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36
Client Language: en-US,en;q=0.9,ro;q=0.8
Page parameter: 1
```

### `test_servlet.py`
Scriptul Python trimite cereri către servlet, imitând un client desktop.
```python
import requests

url = "http://localhost:8080/temalab1/controller"
params = {"page": "2"}

headers = {
    "User-Agent": "PythonDesktopClient/1.0"
}

response = requests.get(url, params=params, headers=headers)

print("Response status:", response.status_code)
print("Response body:", response.text)
```
Output în terminal:
```yaml
Response status: 200
Response body: Selected page: 2
```

---

## Rularea aplicației
Accesând http://localhost:8080/temalab1/ ar trebui să vedem următoarea interfață:

Welcome page:
<img width="1920" height="1022" alt="image" src="https://github.com/user-attachments/assets/7eb09fcf-1916-4341-add0-c8ac28c07510" />

Pagina 1:
<img width="1920" height="1021" alt="image" src="https://github.com/user-attachments/assets/ecdcdc3e-75dd-4825-8ecb-ce3b4af9b82f" />

Pagina 2:
<img width="1920" height="1021" alt="image" src="https://github.com/user-attachments/assets/a78f226b-99d3-43a9-bb38-f873a47786a3" />





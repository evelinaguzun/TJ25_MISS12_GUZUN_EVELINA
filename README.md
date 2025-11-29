# Lab 7-Compulsory + Homework
## Homework

## Funcționalități implementate
- **Tabelă Grade în Baza de Date**-Am creat o tabelă nouă pentru stocarea notelor studenților în aplicația PrefSchedule.
- **Procesare Note doar pentru Cursuri Compulsory**-Notele primite de la QuickGrade sunt stocate doar dacă sunt asociate cursurilor obligatorii.
- **REST Endpoints pentru Grade**-Am implementat endpoint-uri complete pentru obținerea notelor și încărcarea din fișiere CSV.
- **Dead-Letter Queue Handler**-Am configurat DLQ pentru mesajele eșuate cu procesare separată a erorilor.
- **Retry Semantics Testat**-Am testat mecanismul de reîncercare cu 3 tentative și backoff exponential.
- **Integrare RabbitMQ cu QuickGrade**-Sistemul primește evenimente de note prin coadă RabbitMQ și le procesează asincron.

## Testează Curs Inexistent (Merge în DLQ)
<img width="1920" height="122" alt="image" src="https://github.com/user-attachments/assets/0859e30b-6a41-4df8-aa50-a1b049d92c7a" />
<img width="1919" height="513" alt="image" src="https://github.com/user-attachments/assets/e11d0500-5db3-45df-9bf1-14096132957f" />
<img width="1920" height="215" alt="image" src="https://github.com/user-attachments/assets/2da9a1d7-5c91-4ee3-9258-f058589df3c7" />

## Testează Curs Existent (Se salvează în DB)
<img width="1920" height="140" alt="image" src="https://github.com/user-attachments/assets/14a4bf7e-aaf0-4216-9165-7a58f64bd1a1" />
<img width="1920" height="167" alt="image" src="https://github.com/user-attachments/assets/e93c890f-e9e8-42f7-b3f5-8524d6dfebe5" />
<img width="1574" height="221" alt="image" src="https://github.com/user-attachments/assets/145b2a76-6864-4dcf-a206-bf62da63f4b3" />

## Testează Upload CSV
<img width="1767" height="284" alt="image" src="https://github.com/user-attachments/assets/c7b148bd-aaad-4457-976c-908cdbdcdef7" />
<img width="1258" height="238" alt="image" src="https://github.com/user-attachments/assets/275d573d-e076-4c09-bb7e-cbc7536824bd" />


## Compulsory
Am integrat două aplicații Spring Boot prin intermediul RabbitMQ, folosind exchange, queue, routing key și conversie automată JSON.

## Crearea infrastructurii RabbitMQ
<img width="1895" height="887" alt="image" src="https://github.com/user-attachments/assets/a06b655f-24b3-4621-89d7-63779b7b5b85" />
<img width="1899" height="892" alt="image" src="https://github.com/user-attachments/assets/d2644f71-d0e2-449e-9944-b1486cc3acdc" />
<img width="1920" height="759" alt="image" src="https://github.com/user-attachments/assets/ff299f1f-d37c-45b3-82dc-e00c8752277f" />

## Publisher în aplicația -quickgrade-
<img width="1857" height="189" alt="image" src="https://github.com/user-attachments/assets/3149c15b-9eef-431e-9393-eb22fc100153" />
Aplicația quickgrade oferă endpoint **POST /grades** care trimite un mesaj JSON către RabbitMQ.

## Consumer în aplicația -lab4-
Consumerul ascultă queue-ul **grade.queue:**

@RabbitListener(queues = "grade.queue")
public void receiveMessage(GradeMessage msg)

La primirea unei note afișează:
<img width="1920" height="116" alt="image" src="https://github.com/user-attachments/assets/4907fbd6-c0be-4f30-ae2a-fbf9f0fa87ee" />



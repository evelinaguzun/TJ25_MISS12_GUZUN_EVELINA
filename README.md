# Lab 7-Compulsory
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



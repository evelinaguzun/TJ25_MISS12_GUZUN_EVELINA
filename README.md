# Lab 8-Compulsory + Homework
## Homework
## Funcționalități implementate
- Am creat tabela **InstructorPreference** pentru preferințele instructorilor
- Am implementat endpoints pentru gestionarea preferințelor instructorilor
- Am creat **StudentRankingService** pentru calculul scorurilor studenților
- Am implementat algoritmul de matching bazat pe preferințe (ranked)
- Am creat algoritmul random pentru matching fără preferințe
- Am implementat pattern-urile de resilience **Retry** și **Fallback**
- Am creat **StableMatchClient** pentru invocarea serviciului extern
  
## Testare adăugare preferințe instructor
<img width="1899" height="204" alt="image" src="https://github.com/user-attachments/assets/ad5f4808-7fe5-4b95-a755-93c8bf78955e" />
<img width="1870" height="203" alt="image" src="https://github.com/user-attachments/assets/cfc19694-7b80-4573-997f-6df097afe082" />

## Testare calcul scoruri studenți 
<img width="1473" height="84" alt="image" src="https://github.com/user-attachments/assets/7ff53d97-6498-49d4-a32c-8ebf7e4e1f7a" />

## Testare ranked matching
<img width="1580" height="82" alt="image" src="https://github.com/user-attachments/assets/6b155709-91cc-49ce-a0fd-7eaba5cdd400" />

- Au fost selectați cei mai buni 2 studenți după scor: S001 (8.7) și S003 (7.9)
- S002 (7.2) a rămas fără loc - capacitate doar 2

## Testare matching cu fallback
<img width="1577" height="82" alt="image" src="https://github.com/user-attachments/assets/757b032e-296d-4e2d-8421-feda04527c61" />

- A folosit StableMatch service-ul extern care a returnat doar S001.

## Tabelul courses (opțional și compulsory)
<img width="1534" height="712" alt="image" src="https://github.com/user-attachments/assets/7f61b391-5fb3-4018-9922-4235cab5af9b" />

## Tabelul grades (notele reale ale studenților)
<img width="1338" height="660" alt="image" src="https://github.com/user-attachments/assets/6b4f920d-7ae6-46e7-89b9-63041c55b685" />


## Compulsory
## Funcționalități noi implementate:
- Am implementat DTO-urile MatchingRequest și MatchingResponse
- Am creat StableMatchController cu endpoint-ul /api/matching/solve
- Am implementat StableMatchService cu algoritmul de matching
- Am adăugat endpoints suplimentare pentru interogarea asignărilor

## Testare endpoint solve
<img width="1834" height="142" alt="image" src="https://github.com/user-attachments/assets/7ec5a2fd-4f85-4fb4-baf5-5c784b79f557" />

## Testare endpoints assignments, student, course, stats
<img width="1765" height="234" alt="image" src="https://github.com/user-attachments/assets/887241a6-9086-448a-be56-d94075584998" />





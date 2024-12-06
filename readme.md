# Raspodijeljeni sustavi 1. laboratorijska vježba — Prikupljanje i obrada senzorskih podataka

Ovo je rješenje 1. laboratorijske vježbe iz kolegija **Raspodijeljeni sustavi**. Cilj vježbe je implementirati sustav temeljen na klijent-poslužitelj arhitekturi za praćenje i upravljanje parametrima okoliša pomoću REST API-ja i gRPC tehnologije.  

---

## Sadržaj  
- [Opis projekta](#opis-projekta)  
- [Tehnologije](#tehnologije)  
- [Upute za pokretanje](#upute-za-pokretanje)  
- [Funkcionalnosti](#funkcionalnosti)  

---

## Opis projekta  

Sustav se sastoji od dva glavna dijela:  
1. **Poslužitelj**  
   - Pruža REST API za registraciju senzora, pohranu i dohvat očitanja te pronalaženje geografski najbližeg senzora.  
2. **Klijent (senzor)**  
   - Generira senzorska očitanja i kalibrira podatke putem komunikacije s drugim senzorima koristeći gRPC.  

Poslužitelj i klijenti omogućuju simulaciju senzorskog sustava za praćenje okolišnih parametara poput temperature, tlaka, vlažnosti i koncentracija plinova (CO, NO2, SO2).  
<img width="1124" alt="Snimka zaslona 2024-12-07 002123" src="https://github.com/user-attachments/assets/5fbabab5-97d8-4f14-9d5d-69cefc41689a">


---

## Tehnologije  

Projekt je implementiran koristeći sljedeće tehnologije:  
- **Java**: Glavni programski jezik.  
- **Spring Boot**: Za izradu poslužitelja i REST API-ja.  
- **gRPC**: Za komunikaciju između senzora.  
- **Retrofit**: HTTP klijent za klijentske zahtjeve prema poslužitelju.  

---



## Upute za pokretanje  

### Poslužitelj  
1. Otvorite direktorij `server`.  
2. Pokrenite aplikaciju koristeći IDE 

### Klijent
1. Otvorite direktorij client.
2. Pokrenite aplikaciju koristeći IDE

## Funkcionalnosti
### Poslužitelj
1. Registracija senzora putem REST API-ja.
2. Dohvat geografski najbližeg senzora koristeći Haversineovu formulu.
3. Pohrana i dohvat očitanja senzora.
### Klijent (senzor)
1. Generiranje senzorskih očitanja iz ulazne datoteke readings.csv.
2. Razmjena podataka s najbližim susjednim senzorom putem gRPC-a.
3. Kalibracija očitanja koristeći vlastite i susjedne podatke.
4. Pohrana kalibriranih očitanja na poslužitelju.

# Laboratorijska Vježba: Centralizirani Raspodijeljeni Sustav za Senzoriranje Parametara Okoliša

## Opis Projekta

Cilj ove laboratorijske vježbe je izgraditi centralizirani raspodijeljeni sustav za senzoriranje parametara okoliša temeljen na modelu klijent-poslužitelj koristeći tehnologije gRPC i REST web-usluge. U sklopu ovog projekta implementirat ćemo poslužitelj koji upravlja informacijama o senzorima i klijent (senzor) koji šalje podatke o okolišnim parametrima te komunicira s poslužiteljem i najbližim senzorima.

### Sadržaj Projekta

1. **Poslužitelj** (REST API) - omogućava registraciju senzora, određivanje najbližeg susjeda, spremanje očitanja te izlistavanje podataka o registriranim senzorima.
2. **Klijent (Senzor)** - generira podatke o okolišu, komunicira s poslužiteljem i drugim senzorima putem gRPC veze, te šalje podatke poslužitelju nakon kalibracije.

## Tehnologije

- **REST API** - Za komunikaciju između senzora i centralnog poslužitelja, koristeći JSON format.
- **gRPC** - Za razmjenu očitanja između senzora.
- **Programski jezik** - Implementacija je moguća u bilo kojem jeziku, no primjeri su prikazani u Java jeziku.
- **IDE** - Preporuča se korištenje Eclipse, NetBeans, Visual Studio, IntelliJ IDEA ili drugog IDE-a po izboru.

## Arhitektura Sustava

Sustav se sastoji od centralnog poslužitelja i distribuiranih klijenata (senzora):

- **Poslužitelj** - implementiran kao REST web-usluga, pohranjuje podatke o registriranim senzorima, uključujući njihovu geolokaciju i kalibrirana očitanja.
- **Senzori** - distribuirani klijenti koji generiraju očitanja o okolišu i komuniciraju s najbližim susjedima radi kalibracije podataka.

## Struktura Projekta

1. **Server** - Projekt sadrži sve potrebne datoteke za REST poslužitelj.
2. **Client** - Projekt sadrži implementaciju klijenta (senzora) koji se povezuje s poslužiteljem i drugim senzorima putem gRPC-a.

## Funkcionalnost Klijenta (Senzora)

1. **Inicijalizacija i Registracija** - Klijent inicijalno generira nasumičnu geolokaciju i registrira se na poslužitelju.
2. **Generiranje Senzorskih Očitanja** - Senzor generira očitanja za parametre okoliša koristeći podatke iz ulazne datoteke (`readings.csv`).
3. **Razmjena Očitanja s Najbližim Senzorom** - Senzor pronalazi najbližeg susjeda i razmjenjuje podatke putem gRPC veze.
4. **Kalibracija Očitanja** - Na temelju vlastitih i očitanja susjeda, senzor izračunava prosječne vrijednosti parametara okoliša.
5. **Spremanje Kalibriranih Očitanja** - Senzor pohranjuje kalibrirane podatke na poslužitelju.

## Funkcionalnost Poslužitelja

1. **Registracija Senzora** - Poslužitelj omogućava registraciju senzora i pohranjuje podatke o njihovoj geolokaciji.
2. **Najbliži Susjed** - Poslužitelj izračunava najbližeg susjeda koristeći Haversineovu formulu.
3. **Spremanje Očitanja** - Poslužitelj omogućava spremanje kalibriranih očitanja senzora.
4. **Pregled Registriranih Senzora i Očitanja** - Poslužitelj pruža API za pregled svih registriranih senzora i njihovih očitanja.

## Kako Pokrenuti Projekt

1. **Preduvjeti**: Instalirajte gRPC, HTTP klijent (npr. Retrofit), i potrebne knjižnice za REST.
2. **Postavljanje Poslužitelja**:
   - Pokrenite REST poslužitelj i provjerite API-ju putem preglednika ili alata kao što je Postman.
3. **Pokretanje Klijenta (Senzora)**:
   - Pokrenite klijent sa slučajnim geolokacijama i omogućite razmjenu očitanja putem gRPC veze.

## Struktura Arhive za Predaju

Prilikom predaje, arhiva projekta (`ime_prezime_jmbag.zip`) treba sadržavati:

- **Direktorij poslužitelja** s izvornim kodom REST poslužitelja.
- **Direktorij klijenta** s izvornim kodom gRPC klijenta (senzora).

## Primjeri JSON Poruka

1. **Registracija Senzora**

   ```json
   {
     "latitude": 45.75,
     "longitude": 15.87,
     "ip": "127.0.0.1",
     "port": 8080
   }
   ```

2. **Zahtjev za Najbližeg Susjeda**

   ```json
   {
     "latitude": 45.76,
     "longitude": 15.88,
     "ip": "127.0.0.1",
     "port": 8081
   }
   ```

3. **Spremanje Kalibriranih Očitanja**

   ```json
   {
     "temperature": 32.0,
     "pressure": 1109.0,
     "humidity": 40.0,
     "co": 547.0,
     "so2": 18.0
   }
   ```

4. **Popis Registriranih Senzora**
   ```json
   [
     {
       "id": 1,
       "latitude": 45.75,
       "longitude": 15.87,
       "ip": "127.0.0.1",
       "port": 8080
     },
     {
       "id": 2,
       "latitude": 45.76,
       "longitude": 15.88,
       "ip": "127.0.0.1",
       "port": 8081
     }
   ]
   ```

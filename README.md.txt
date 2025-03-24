# System Klienta Bankowego 


To prosty system do zarządzania kontami bankowymi napisany w Javie z użyciem Spring Boota. Pozwala na zakładanie kont, wykonywanie przelewów, wypłat, wpłat, podgląd stanu konta oraz historię transakcji.

Dane użytkowników zapisywane są lokalnie w plikach `.json`. System waliduje też PESEL, IBAN oraz email studencki.

## Główne funkcje

-  Tworzenie konta bankowego
-  Wpłaty, wypłaty i przelewy
-  Podgląd konta i historii transakcji
-  Walidacja danych użytkownika
-  Usuwanie konta
-  Edycja danych klienta

## Technologie

- Java 17
- Spring Boot
- Jakarta Bean Validation
- Maven
- Postman (do testów)
- JSON (Jackson)


## Struktura katalogów

System-klienta-bankowego : 

config → JacksonConfig
controller → BankAccountController, GlobalExceptionHandler
interfaces → BankAccountOperations, FileOperationsInterface, JsonSerializable
model → BankAccount, BankAccountDTO, Transaction,
service → BankAccountService,
utils → FileOperations,
validation → BankApplication,
System-kienta-bankowego.java,
README.md.


## Walidacje

- **PESEL** – długość, cyfry, suma kontrolna
- **Email** – tylko z domeną `@student.tu.kielce.pl`
- **IBAN** – sprawdzanie cyfr kontrolnych (ISO 13616)


## Endpointy REST API

| Metoda | Endpoint                                | Opis                       |
|--------|------------------------------------------|----------------------------|
| POST   | `/accounts`                              | Tworzy nowe konto          |
| GET    | `/accounts/{pesel}`                      | Podgląd konta              |
| POST   | `/accounts/{pesel}/deposit?amount=100`   | Wpłata                     |
| POST   | `/accounts/{pesel}/withdraw?amount=50`   | Wypłata                    |
| POST   | `/accounts/transfer`                     | Przelew do innego konta    |
| PUT    | `/accounts/{pesel}`                      | Aktualizacja danych klienta|
| DELETE | `/accounts/{pesel}`                      | Usunięcie konta            |

## Jak uruchomić?

1. Otwórz projekt w IntelliJ.
2. Upewnij się, że port `8080` nie jest zajęty.
3. Odpal klasę `BankApplication`.
4. Testuj API np. w Postmanie (lub przez plik `requests.http`).




# Wallet Balance Counter

This is a test project for the company I'm applying now. 

### Domain

The domain is a wallet balance counter service. It can apply transaction info and return balance amount 
in the end of each hour between requested period.

API Methods:
- POST `/transaction`
- POST `/get-balance-history`

### Business restrictions

- Transaction with datetime in the future can't be applied
- Transaction with datetime older than one hour from current moment can't be applied
- Balance history request with incorrect startDatetime and endDatetime will return empty array.

### How to run

This project requires JDK 17. If it isn't set globally, you can set path to it by `org.gradle.java.home` 
property in *gradle.properties* file.

Then just run command bellow.

```shell
./gradlew run
```

Server will be available at `http://localhost:8080/`.
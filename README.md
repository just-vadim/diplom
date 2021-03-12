# Дипломный проект профессии «Тестировщик»
## Документация
- [План автоматизации тестирования](https://github.com/just-vadim/diplom/blob/master/documentation/Plan.md)
- Отчёт по итогам автоматизированного тестирования
- Отчётных по итогам автоматизации

## Инструкция по запуску
1. **Склонировать проект из репозитория**
    - Открыть `Git Bash` и выполнить команду `git clone https://github.com/just-vadim/diplom`
2. **Открыть проект в IDE**
    - Открыть проект с помощью `IntelliJ IDEA`
3. **Запустить контейнер с СУБД и симулятором сервисов Банка**
    - Внутри `IntelliJ IDEA` запустить терминал и выполнить команду `docker-compose up`
4. **Запустить приложение**
    1. Запуск с подключением к MySQL
        - Открыть новое окно терминала и выполнить команду `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -jar aqa-shop.jar`
    2. Запуск с подключением к PostgreSQL
        - Открыть новое окно терминала и выполнить команду `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -jar aqa-shop.jar`
5. **Запустить тесты**
    1. Запуск с подключением к MySQL
        - Открыть новое окно терминала и выполнить команду `gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app -Ddb.user=app -Ddb.password=pass`
    2. Запуск с подключением к PostgreSQL
        - Открыть новое окно терминала и выполнить команду `gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app -Ddb.user=app -Ddb.password=pass`
    
    Адрес SUT по умолчанию: http://localhost:8080. Для изменения адреса необходимо добавить параметр `-Dsut.url=url` к команде запуска тестов.

6. **Сформировать отчёт**
    - Выполнить в терминале команду `gradlew allureReport`, затем команду `gradlewServe`

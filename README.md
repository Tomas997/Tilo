# Система управління тренуваннями Tilo

Tilo - це веб-додаток, розроблений для керування тренувальними сесіями йоги. Він забезпечує зручну платформу для реєстрації користувачів, бронювання сесій та перегляду історії замовлень. Тренери можуть отримати доступ до своїх профілів, щоб переглядати розклад сесій на обрану дату. Адміністратори можуть додавати тренерів, створювати та редагувати розклад, переглядати списки користувачів, які записані на сесії, та видаляти сесії за необхідності.

## Використані технології

- Java Spring framework для бекенду
- Шаблонізатор Thymeleaf для динамічних веб-сторінок
- База даних H2 для зберігання даних
- Flyway для міграції бази даних
- MapStruct для відображення об'єктів


## Як запустити

1. Клонуйте цей репозиторій.
2. Відкрийте проект у вашій IDE (рекомендовано IntelliJ IDEA).
3. Побудуйте проект для вирішення залежностей.
4. Налаштуйте з'єднання з базою даних у файлі `application.yaml`.
5. Запустіть головний файл програми.

Або 
1. Клонуйте цей репозиторій.
2. Налаштуйте з'єднання з базою даних у файлі `application.yaml`.
3. Перевірте наявність Maven та Java
4. Виконайте команду
```bash
mvn spring-boot:run

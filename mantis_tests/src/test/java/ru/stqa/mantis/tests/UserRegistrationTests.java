package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        //создать пользователя (адрес) на почтовом сервире (JamesHelper)
        //открывааем браузер и заполняем форму создания и отправляем (браузер-надо создать помошника)
        //ждем почту (MailHelper)
        //извлекаем ссылку из письма
        //возвращаемся обратно в браузер , проходим оп ссылке из письма и заверщаем регистрацию пользователя (браузер-надо создать помошника)
        //проверяем, что пользователь может залогинется (HttpSessionHelper)
    }
}

package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.stqa.mantis.common.CommonFunctions;

public class UserRegistrationTests extends TestBase {


    @ParameterizedTest
    @ValueSource(strings = "alice")
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUser(email, "password"); //создать пользователя (адрес) на почтовом сервире (JamesHelper)
        //открывааем браузер и заполняем форму создания и отправляем (браузер-надо создать помошника)
        //ждем почту (MailHelper)
        //извлекаем ссылку из письма
        //возвращаемся обратно в браузер , проходим оп ссылке из письма и заверщаем регистрацию пользователя (браузер-надо создать помошника)
        //проверяем, что пользователь может залогинется (HttpSessionHelper)
    }
}

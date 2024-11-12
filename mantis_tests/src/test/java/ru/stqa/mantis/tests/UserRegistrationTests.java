package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class UserRegistrationTests extends TestBase {


    @ParameterizedTest
    @ValueSource(strings = "venera4")
    void canRegisterUser(String username) {
        var email = format("%s@localhost", username);
        app.jamesCli().addUser(email, "password"); //создать пользователя (адрес) на почтовом сервире (JamesHelper)
        app.registration().registrationForm(username, email);//открывааем браузер и заполняем форму создания и отправляем (браузер-надо создать помошника)
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(20));//ждем почту (MailHelper)
        String url = "";
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
                            }//извлекаем ссылку из письма
        app.registration().openBrowserUrl2(url, "password");//возвращаемся обратно в браузер , проходим по ссылке из письма и завершаем регистрацию пользователя (браузер-надо создать помошника)
        app.http().login(username, "password");//проверяем, что пользователь может залогинется (HttpSessionHelper)
    }

}

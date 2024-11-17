package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.String.format;

public class UserRegistrationTests extends TestBase {

    private static Stream<Arguments> generateRandomUsernames() {
        return Stream.generate(() -> {
            int length = new Random().nextInt(8) + 1; // Длина от 1 до 8 символов включительно
            String username = CommonFunctions.randomString(length);
            return Arguments.of(username);
        }).limit(1); // Ограничить количество итераций до 10
    }


    @ParameterizedTest
    @MethodSource("generateRandomUsernames")
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


public static Stream<String> randomUser() {
    return Stream.of(CommonFunctions.randomString(8));
}

     @ParameterizedTest
      @MethodSource("randomUser")
        void canCreateUser(String user) {
            var email = String.format("%s@localhost", user);
            var password = "password";
            app.jamesApi().addUser(email, password);

          app.rest().createUser(new UserData()
                .withUsername(user)
                .withEmail(email));

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
          app.http().login(user, "password");//проверяем, что пользователь может залогинется (HttpSessionHelper)
        }
}

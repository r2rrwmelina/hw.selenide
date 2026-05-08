package ru.netology.cardform;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {

    public String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldToBookCard() {
      open("http://localhost:9999/");

      String planningDate = generateDate(3, "dd.MM.yyyy");

      $("[placeholder='Город']").setValue("Астрахань");
      $("[type='tel']")
              .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
              .setValue(planningDate);
      $("[name='name']").setValue("Иванова Анна");
      $("[name='phone']").setValue("+79276665253");
      $(".checkbox__box").click();
      $(".button").click();
      $("[data-test-id='notification']")
              .should(Condition.visible, Duration.ofSeconds(15))
              .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate));
    }

//    @Test
//    public void shouldToBookCardWithPopUp() {
//        open("http://localhost:9999/");
//
//        String planningDay = generateDate(7, "dd.MM.yyyy");
//
//        $("[placeholder='Город']").setValue("Мо");
//        $$(".menu-item").find(exactText("Москва")).click();
//        $("[type='tel']").click();
//    }
}

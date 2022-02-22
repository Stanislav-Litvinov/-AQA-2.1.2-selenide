package netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestAppOrder {

    @Test
    void shouldTestValidationEmptyFieldName() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input.input__control").setValue("+79633414412");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=\"button\"]").click();
        $(".input_invalid ").shouldHave(exactText("Фамилия и имя\n" +
                "Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestValidationFieldNameInEnglish() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id=name] input").setValue("Sergio Bree");
        form.$("[data-test-id=phone] input.input__control").setValue("+79633414412");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=\"button\"]").click();
        $(".input_invalid ").shouldHave(exactText("Фамилия и имя\n" +
                "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestValidationEmptyFieldNumber() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input.input__control").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=\"button\"]").click();
        $(".input_invalid ").shouldHave(exactText("Мобильный телефон\n" +
                "Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestValidationFieldNumberWithLessThen11Numbers() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input.input__control").setValue("+7963341");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=\"button\"]").click();
        $(".input_invalid ").shouldHave(exactText("Мобильный телефон\n" +
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestValidationFieldNumberWithMoreThen11Numbers() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input.input__control").setValue("+796334144121");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=\"button\"]").click();
        $(".input_invalid ").shouldHave(exactText("Мобильный телефон\n" +
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestValidationFieldNumberWithoutPlus() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input.input__control").setValue("79633414412");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=\"button\"]").click();
        $(".input_invalid ").shouldHave(exactText("Мобильный телефон\n" +
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestValidationFieldNumberWithSymbols() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input.input__control").setValue("*$633414412");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=\"button\"]").click();
        $(".input_invalid ").shouldHave(exactText("Мобильный телефон\n" +
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestValidationFieldNumberWithString() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input.input__control").setValue("Сергей Коржов");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=\"button\"]").click();
        $(".input_invalid ").shouldHave(exactText("Мобильный телефон\n" +
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
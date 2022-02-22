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
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79633414412");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText(
                "Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestValidationFieldNameInEnglish() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id='name'] input").setValue("Sergio Bree");
        form.$("[data-test-id=phone] input").setValue("+79633414412");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия " +
                "указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestValidationFieldWithDoubleSurname() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id='name'] input").setValue("Николай Римский-Корсаков");
        form.$("[data-test-id=phone] input").setValue("+79633414412");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $("[data-test-id='order-success']").shouldHave(exactText("  Ваша заявка успешно отправлена!" +
                " Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestValidationEmptyFieldNumber() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText(
                "Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestValidationFieldNumberWithLessThen11Numbers() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input").setValue("+7963341");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText(
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestValidationFieldNumberWithMoreThen11Numbers() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input").setValue("+796334144121");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText(
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestValidationFieldNumberWithoutPlus() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input").setValue("79633414412");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText(
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestValidationFieldNumberWithSymbols() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input").setValue("*$633414412");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText(
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestValidationFieldNumberWithString() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input").setValue("Сергей Коржов");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText(
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldTestValidationCheckboxWithoutAgreement() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Сергей Коржов");
        form.$("[data-test-id=phone] input").setValue("+79012345678");
        form.$("[type='button']").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки" +
                " и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
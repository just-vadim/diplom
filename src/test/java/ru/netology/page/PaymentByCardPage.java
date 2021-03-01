package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentByCardPage {
    private SelenideElement heading = $$(".heading_size_m").find(exactText("Оплата по карте"));
    private SelenideElement cardNumberInputFiled = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthInputField = $(byText("Месяц")).$("[class='input__control']");
    private SelenideElement yearInputField = $(byText("Год")).$("[class='input__control']");
    private SelenideElement cardholderInputField = $(byText("Владелец")).$("[class='input__control']");
    private SelenideElement CVVInputField = $(byText("CVC/CVV")).$("[class='input__control']");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

}

package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentOnCreditPage {
    private SelenideElement heading = $$(".heading_size_m").find(exactText("Кредит по данным карты"));
    private SelenideElement cardNumberInputFiled = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthInputField = $(byText("Месяц")).parent().$("[class='input__control']");
    private SelenideElement yearInputField = $(byText("Год")).parent().$("[class='input__control']");
    private SelenideElement cardholderInputField = $(byText("Владелец")).parent().$("[class='input__control']");
    private SelenideElement cvvInputField = $(byText("CVC/CVV")).parent().$("[class='input__control']");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    public PaymentOnCreditPage() {
        heading.shouldBe(visible);
        cardNumberInputFiled.shouldBe(visible);
        monthInputField.shouldBe(visible);
        yearInputField.shouldBe(visible);
        cardholderInputField.shouldBe(visible);
        cvvInputField.shouldBe(visible);
        continueButton.shouldBe(visible);
    }

    public void inputData() {
        cardNumberInputFiled.setValue("4444 4444 4444 4441");
        monthInputField.setValue("10");
        yearInputField.setValue("23");
        cardholderInputField.setValue("John Doe");
        cvvInputField.setValue("777");
        continueButton.click();
    }
}

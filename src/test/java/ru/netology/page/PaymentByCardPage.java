package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Card;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentByCardPage {
    private SelenideElement heading = $$(".heading_size_m").find(exactText("Оплата по карте"));
    private SelenideElement cardNumberInputFiled = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthInputField = $(byText("Месяц")).parent().$("[class='input__control']");
    private SelenideElement yearInputField = $(byText("Год")).parent().$("[class='input__control']");
    private SelenideElement cardholderInputField = $(byText("Владелец")).parent().$("[class='input__control']");
    private SelenideElement cvvInputField = $(byText("CVC/CVV")).parent().$("[class='input__control']");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement successMsg = $(".notification_status_ok");
    private SelenideElement successMsgCloseButton = $(".notification_status_ok").parent().$(".notification__closer");

    public void successMsgClose() {
        successMsgCloseButton.click();
    }
    public void successMsgWait() {
        successMsg.waitUntil(visible, 15000);
    }

    public PaymentByCardPage() {
        heading.shouldBe(visible);
        cardNumberInputFiled.shouldBe(visible);
        monthInputField.shouldBe(visible);
        yearInputField.shouldBe(visible);
        cardholderInputField.shouldBe(visible);
        cvvInputField.shouldBe(visible);
        continueButton.shouldBe(visible);
    }

    public void inputData(Card card) {
        cardNumberInputFiled.setValue(card.getNumber());
        monthInputField.setValue(card.getMonth());
        yearInputField.setValue(card.getYear());
        cardholderInputField.setValue(card.getCardholder());
        cvvInputField.setValue(card.getCvv());
        continueButton.click();
    }
}
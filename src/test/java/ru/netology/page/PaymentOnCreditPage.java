package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Card;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentOnCreditPage {
    private SelenideElement heading = $$(".heading_size_m").find(exactText("Кредит по данным карты"));
    private SelenideElement cardNumberInputFiled = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement cardNumberBadFormatError = cardNumberInputFiled.parent().parent().$(byText("Неверный формат"));
    private SelenideElement monthInputField = $(byText("Месяц")).parent().$("[class='input__control']");
    private SelenideElement monthBadFormatError = monthInputField.parent().parent().$(byText("Неверный формат"));
    private SelenideElement monthInvalidExpirationDateError = monthInputField.parent().parent().$(byText("Неверно указан срок действия карты"));
    private SelenideElement yearInputField = $(byText("Год")).parent().$("[class='input__control']");
    private SelenideElement yearBadFormatError = yearInputField.parent().parent().$(byText("Неверный формат"));
    private SelenideElement yearCardExpiredError = yearInputField.parent().parent().$(byText("Истёк срок действия карты"));
    private SelenideElement yearInvalidExpirationDateError = yearInputField.parent().parent().$(byText("Неверно указан срок действия карты"));
    private SelenideElement cardholderInputField = $(byText("Владелец")).parent().$("[class='input__control']");
    private SelenideElement cardholderRequiredError = cardholderInputField.parent().parent().$(byText("Поле обязательно для заполнения"));
    private SelenideElement cvvInputField = $(byText("CVC/CVV")).parent().$("[class='input__control']");
    private SelenideElement cvvBadFormatError = cvvInputField.parent().parent().$(byText("Неверный формат"));
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement successMsg = $(".notification_status_ok");
    private SelenideElement successMsgCloseButton = $(".notification_status_ok").$(".notification__closer");
    private SelenideElement declineMsg = $(".notification_status_error");
    private SelenideElement declineMsgCloseButton = $(".notification_status_error").$(".notification__closer");

    public PaymentOnCreditPage() {
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

    public void successMsgClose() {
        successMsgCloseButton.click();
    }

    public void declineMsgClose() {
        declineMsgCloseButton.click();
    }

    public void successMsgWait() {
        successMsg.waitUntil(visible, 15000);
    }

    public void declineMsgWait() {
        declineMsg.waitUntil(visible, 15000);
    }

    public void cardNumberBadFormatErrorWait() {
        cardNumberBadFormatError.waitUntil(visible, 3000);
    }

    public void monthBadFormatErrorWait() {
        monthBadFormatError.waitUntil(visible, 3000);
    }

    public void monthInvalidExpirationDateErrorWait() {
        monthInvalidExpirationDateError.waitUntil(visible, 3000);
    }

    public void yearBadFormatErrorWait() {
        yearBadFormatError.waitUntil(visible, 3000);
    }

    public void yearCardExpiredErrorWait() {
        yearCardExpiredError.waitUntil(visible, 3000);
    }

    public void yearInvalidExpirationDateErrorWait() {
        yearInvalidExpirationDateError.waitUntil(visible, 3000);
    }

    public void cardholderRequiredErrorWait() {
        cardholderRequiredError.waitUntil(visible, 3000);
    }

    public void cvvBadFormatErrorWait() {
        cvvBadFormatError.waitUntil(visible, 3000);
    }
}
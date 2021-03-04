package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class PaymentByCardTest {
    @BeforeEach
    void setUpEach() {
        open("http://localhost:8080/");
    }

    @Test
    void test() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getValidCardInfo());
        paymentByCardPage.successMsgWait();
        paymentByCardPage.successMsgClose();
    }
}
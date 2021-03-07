package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.getValidCardInfo;
import static ru.netology.data.SQLHelper.clearDB;
import static ru.netology.data.SQLHelper.getPaymentOnCreditStatus;

public class PaymentOnCreditTest {
    @BeforeEach
    void setUpEach() {
        open("http://localhost:8080/");
    }

    @AfterEach
    void clearDataBase() {
        clearDB();
    }

    @Test
    void paymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getValidCardInfo());
        paymentOnCreditPage.successMsgWait();
        paymentOnCreditPage.successMsgClose();
        assertEquals("APPROVED", getPaymentOnCreditStatus());
    }
}
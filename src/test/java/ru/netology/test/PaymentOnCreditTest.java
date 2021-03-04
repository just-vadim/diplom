package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;

public class PaymentOnCreditTest {
    @BeforeEach
    void setUpEach() {
        open("http://localhost:8080/");
    }

    @Test
    void test() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData();
    }
}

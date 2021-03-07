package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.DataHelper.getTwoNumbersCVVCardInfo;
import static ru.netology.data.SQLHelper.*;

public class PaymentOnCreditTest {
    @BeforeEach
    void setUpEach() {
        open("http://localhost:8080/");
    }

    @AfterEach
    void clearDataBase() {
        clearDB();
    }

    /* 1 */
    @Test
    void validCardPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getValidCardInfo());
        paymentOnCreditPage.successMsgWait();
        paymentOnCreditPage.successMsgClose();
        assertEquals("APPROVED", getPaymentOnCreditStatus());
    }

    /* 2 */
    @Test
    void declinedCardPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getDeclinedCardInfo());
        paymentOnCreditPage.declineMsgWait();
        paymentOnCreditPage.declineMsgClose();
        assertEquals("DECLINED", getPaymentOnCreditStatus());
    }

    /* 3 */
    @Test
    void allFieldsEmptyPaymentOnCreditTest () {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getEmptyFieldsCardInfo());
        paymentOnCreditPage.cardNumberBadFormatErrorWait();
        paymentOnCreditPage.monthBadFormatErrorWait();
        paymentOnCreditPage.yearBadFormatErrorWait();
        paymentOnCreditPage.cardholderRequiredErrorWait();
        paymentOnCreditPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 4 */
    @Test
    void cardNumberFieldEmptyPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getEmptyCardNumberFieldCardInfo());
        paymentOnCreditPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 5 */
    @Test
    void monthFiledEmptyPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getEmptyMonthFieldCardInfo());
        paymentOnCreditPage.monthBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 6 */
    @Test
    void yearFieldEmptyPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getEmptyYearFiledCardInfo());
        paymentOnCreditPage.yearBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 7 */
    @Test
    void cardholderFiledEmptyPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getEmptyCardholderFiledCardInfo());
        paymentOnCreditPage.cardholderRequiredErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 8 */
    @Test
    void cvvFieldEmptyPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getEmptyCVVFieldCardInfo());
        paymentOnCreditPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 9 */
    @Test
    void invalidCardNumberPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getInvalidCardNumberCardInfo());
        paymentOnCreditPage.declineMsgWait();
        paymentOnCreditPage.declineMsgClose();
        assertEquals("DECLINED", getPaymentOnCreditStatus());
    }

    /* 10 */
    @Test
    void nonexistentCardNumberPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getNonexistentCardNumberCardInfo());
        paymentOnCreditPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 11 */
    @Test
    void incompleteCardNumberPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getIncompleteCardNumberCardInfo());
        paymentOnCreditPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 12 */
    @Test
    void zerosInMonthFiledPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getZerosInMonthFiledCardInfo());
        paymentOnCreditPage.monthBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 13 */
    @Test
    void mothWithoutZeroFirstCharacterPaymentOnCreditTest () {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getMothWithoutZeroFirstCharacterCardInfo());
        paymentOnCreditPage.monthBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 14 */
    @Test
    void nonexistentMonthPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getNonexistentMonthCardInfo());
        paymentOnCreditPage.monthInvalidExpirationDateErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 15 */
    @Test
    void zeroInYearFieldPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getZeroInYearFieldCardInfo());
        paymentOnCreditPage.yearBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 16 */
    @Test
    void expiredCardPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getExpiredCardCardInfo());
        paymentOnCreditPage.yearCardExpiredErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 17 */
    @Test
    void invalidYearPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getInvalidYearCardInfo());
        paymentOnCreditPage.yearInvalidExpirationDateErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 18 */
    @Test
    void cardholderOnlyFirstNamePaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getCardholderOnlyFirstNameCardInfo());
        paymentOnCreditPage.successMsgWait();
        paymentOnCreditPage.successMsgClose();
        assertEquals("APPROVED", getPaymentOnCreditStatus());
    }

    /* 19 */
    @Test
    void longCardholderNamePaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getLongCardholderNameCardInfo());
        paymentOnCreditPage.successMsgWait();
        paymentOnCreditPage.successMsgClose();
        assertEquals("APPROVED", getPaymentOnCreditStatus());
    }

    /* 20 */
    @Test
    void cyrillicCardholderNamePaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getCyrillicCardholderNameCardInfo());
        paymentOnCreditPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 21 */
    @Test
    void specialCharactersInCardholderNamePaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getSpecialCharactersInCardholderNameCardInfo());
        paymentOnCreditPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 22 */
    @Test
    void numbersInCardholderNamePaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getNumbersInCardholderNameCardInfo());
        paymentOnCreditPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 23 */
    @Test
    void oneNumberCVVPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getOneNumberCVVCardInfo());
        paymentOnCreditPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }

    /* 24 */
    @Test
    void twoNumbersCVVPaymentOnCreditTest() {
        val startPage = new StartPage();
        val paymentOnCreditPage = startPage.selectPaymentOnCreditPage();
        paymentOnCreditPage.inputData(getTwoNumbersCVVCardInfo());
        paymentOnCreditPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentOnCreditStatus());
    }
}
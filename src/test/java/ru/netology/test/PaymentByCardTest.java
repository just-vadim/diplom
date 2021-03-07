package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLHelper.*;

public class PaymentByCardTest {
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
    void validCardPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getValidCardInfo());
        paymentByCardPage.successMsgWait();
        paymentByCardPage.successMsgClose();
        assertEquals("APPROVED", getPaymentByCardStatus());
    }

    /* 2 */
    @Test
    void declinedCardPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getDeclinedCardInfo());
        paymentByCardPage.declineMsgWait();
        paymentByCardPage.declineMsgClose();
        assertEquals("DECLINED", getPaymentByCardStatus());
    }

    /* 3 */
    @Test
    void allFieldsEmptyPaymentTest () {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getEmptyFieldsCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        paymentByCardPage.monthBadFormatErrorWait();
        paymentByCardPage.yearBadFormatErrorWait();
        paymentByCardPage.cardholderRequiredErrorWait();
        paymentByCardPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 4 */
    @Test
    void cardNumberFieldEmptyPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getEmptyCardNumberFieldCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 5 */
    @Test
    void monthFiledEmptyPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getEmptyMonthFieldCardInfo());
        paymentByCardPage.monthBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 6 */
    @Test
    void yearFieldEmptyPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getEmptyYearFiledCardInfo());
        paymentByCardPage.yearBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 7 */
    @Test
    void cardholderFiledEmptyPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getEmptyCardholderFiledCardInfo());
        paymentByCardPage.cardholderRequiredErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 8 */
    @Test
    void cvvFieldEmptyPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getEmptyCVVFieldCardInfo());
        paymentByCardPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 9 */
    @Test
    void invalidCardNumberPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getInvalidCardNumberCardInfo());
        paymentByCardPage.declineMsgWait();
        paymentByCardPage.declineMsgClose();
        assertEquals("DECLINED", getPaymentByCardStatus());
    }

    /* 10 */
    @Test
    void nonexistentCardNumberPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getNonexistentCardNumberCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 11 */
    @Test
    void incompleteCardNumberPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getIncompleteCardNumberCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 12 */
    @Test
    void zerosInMonthFiledPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getZerosInMonthFiledCardInfo());
        paymentByCardPage.monthBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 13 */
    @Test
    void mothWithoutZeroFirstCharacterPaymentTest () {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getMothWithoutZeroFirstCharacterCardInfo());
        paymentByCardPage.monthBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 14 */
    @Test
    void nonexistentMonthPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getNonexistentMonthCardInfo());
        paymentByCardPage.monthInvalidExpirationDateErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 15 */
    @Test
    void zeroInYearFieldPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getZeroInYearFieldCardInfo());
        paymentByCardPage.yearBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 16 */
    @Test
    void expiredCardPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getExpiredCardCardInfo());
        paymentByCardPage.yearCardExpiredErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 17 */
    @Test
    void invalidYearPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getInvalidYearCardInfo());
        paymentByCardPage.yearInvalidExpirationDateErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 18 */
    @Test
    void cardholderOnlyFirstNamePaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getCardholderOnlyFirstNameCardInfo());
        paymentByCardPage.successMsgWait();
        paymentByCardPage.successMsgClose();
        assertEquals("APPROVED", getPaymentByCardStatus());
    }

    /* 19 */
    @Test
    void longCardholderNamePaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getLongCardholderNameCardInfo());
        paymentByCardPage.successMsgWait();
        paymentByCardPage.successMsgClose();
        assertEquals("APPROVED", getPaymentByCardStatus());
    }

    /* 20 */
    @Test
    void cyrillicCardholderNamePaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getCyrillicCardholderNameCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 21 */
    @Test
    void specialCharactersInCardholderNamePaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getSpecialCharactersInCardholderNameCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 22 */
    @Test
    void numbersInCardholderNamePaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getNumbersInCardholderNameCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 23 */
    @Test
    void oneNumberCVVPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getOneNumberCVVCardInfo());
        paymentByCardPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 24 */
    @Test
    void twoNumbersCVVPaymentTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getTwoNumbersCVVCardInfo());
        paymentByCardPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }
}
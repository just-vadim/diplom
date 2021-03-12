package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLHelper.*;

public class PaymentByCardTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUpEach() {
        open(System.getProperty("sut.url"));
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    void clearDataBase() {
        clearDB();
    }

    /* 1 */
    @Test
    void validCardPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getValidCardInfo());
        paymentByCardPage.successMsgWait();
        paymentByCardPage.successMsgClose();
        assertEquals("APPROVED", getPaymentByCardStatus());
    }

    /* 2 */
    @Test
    void declinedCardPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getDeclinedCardInfo());
        paymentByCardPage.declineMsgWait();
        paymentByCardPage.declineMsgClose();
        assertEquals("DECLINED", getPaymentByCardStatus());
    }

    /* 3 */
    @Test
    void allFieldsEmptyPaymentByCardTest () {
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
    void cardNumberFieldEmptyPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getEmptyCardNumberFieldCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 5 */
    @Test
    void monthFiledEmptyPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getEmptyMonthFieldCardInfo());
        paymentByCardPage.monthBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 6 */
    @Test
    void yearFieldEmptyPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getEmptyYearFiledCardInfo());
        paymentByCardPage.yearBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 7 */
    @Test
    void cardholderFiledEmptyPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getEmptyCardholderFiledCardInfo());
        paymentByCardPage.cardholderRequiredErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 8 */
    @Test
    void cvvFieldEmptyPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getEmptyCVVFieldCardInfo());
        paymentByCardPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 9 */
    @Test
    void invalidCardNumberPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getInvalidCardNumberCardInfo());
        paymentByCardPage.declineMsgWait();
        paymentByCardPage.declineMsgClose();
        assertEquals("DECLINED", getPaymentByCardStatus());
    }

    /* 10 */
    @Test
    void nonexistentCardNumberPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getNonexistentCardNumberCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 11 */
    @Test
    void incompleteCardNumberPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getIncompleteCardNumberCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 12 */
    @Test
    void zerosInMonthFiledPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getZerosInMonthFiledCardInfo());
        paymentByCardPage.monthBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 13 */
    @Test
    void mothWithoutZeroFirstCharacterPaymentByCardTest () {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getMothWithoutZeroFirstCharacterCardInfo());
        paymentByCardPage.monthBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 14 */
    @Test
    void nonexistentMonthPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getNonexistentMonthCardInfo());
        paymentByCardPage.monthInvalidExpirationDateErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 15 */
    @Test
    void zeroInYearFieldPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getZeroInYearFieldCardInfo());
        paymentByCardPage.yearBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 16 */
    @Test
    void expiredCardPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getExpiredCardCardInfo());
        paymentByCardPage.yearCardExpiredErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 17 */
    @Test
    void invalidYearPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getInvalidYearCardInfo());
        paymentByCardPage.yearInvalidExpirationDateErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 18 */
    @Test
    void cardholderOnlyFirstNamePaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getCardholderOnlyFirstNameCardInfo());
        paymentByCardPage.successMsgWait();
        paymentByCardPage.successMsgClose();
        assertEquals("APPROVED", getPaymentByCardStatus());
    }

    /* 19 */
    @Test
    void longCardholderNamePaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getLongCardholderNameCardInfo());
        paymentByCardPage.successMsgWait();
        paymentByCardPage.successMsgClose();
        assertEquals("APPROVED", getPaymentByCardStatus());
    }

    /* 20 */
    @Test
    void cyrillicCardholderNamePaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getCyrillicCardholderNameCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 21 */
    @Test
    void specialCharactersInCardholderNamePaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getSpecialCharactersInCardholderNameCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 22 */
    @Test
    void numbersInCardholderNamePaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getNumbersInCardholderNameCardInfo());
        paymentByCardPage.cardNumberBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 23 */
    @Test
    void oneNumberCVVPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getOneNumberCVVCardInfo());
        paymentByCardPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }

    /* 24 */
    @Test
    void twoNumbersCVVPaymentByCardTest() {
        val startPage = new StartPage();
        val paymentByCardPage = startPage.selectPaymentByCardPage();
        paymentByCardPage.inputData(getTwoNumbersCVVCardInfo());
        paymentByCardPage.cvvBadFormatErrorWait();
        assertEquals("NO DATA", getPaymentByCardStatus());
    }
}
package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Random;

public class DataHelper {
    static Faker faker = new Faker();

    private static String approvedCard = "4444 4444 4444 4441";
    private static String declinedCard = "4444 4444 4444 4442";

    private static String generateMonth() {
        Random random = new Random();
        int num = random.nextInt(11) + 1;
        if (num < 10) {
            return "0" + num;
        } else {
            return String.valueOf(num);
        }
    }

    /* 1 */
    public static Card getValidCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 2 */
    public static Card getDeclinedCardInfo() {
        return new Card(declinedCard,
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 3 done */
    public static Card getEmptyFieldsCardInfo() {
        return new Card("",
                "",
                "",
                "",
                "");
    }

    /* 4 */
    public static Card getEmptyCardNumberFieldCardInfo() {
        return new Card("",
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 5 */
    public static Card getEmptyMonthFieldCardInfo() {
        return new Card(approvedCard,
                "",
                "",
                "",
                faker.numerify("###"));
    }

    /* 6 */
    public static Card getEmptyYearFiledCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 7 */
    public static Card getEmptyCardholderFiledCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 8 */
    public static Card getEmptyCVVFieldCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "",
                "",
                "");
    }

    /* 9 */
    public static Card getInvalidCardNumberCardInfo() {
        return new Card("4276380043211234",
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 10 */
    public static Card getNonexistentCardNumberCardInfo() {
        return new Card("1234432112344321",
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 11 */
    public static Card getIncompleteCardNumberCardInfo() {
        return new Card("444444444444444",
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 12 */
    public static Card getZerosInMonthFiledCardInfo() {
        return new Card(approvedCard,
                "00",
                "",
                "",
                faker.numerify("###"));
    }

    /* 13 */
    public static Card getMothWithoutZeroFirstCharacterCardInfo() {
        return new Card(approvedCard,
                "5",
                "",
                "",
                faker.numerify("###"));
    }

    /* 14 */
    public static Card getNonexistentMonthCardInfo() {
        return new Card(approvedCard,
                "68",
                "",
                "",
                faker.numerify("###"));
    }

    /* 15 */
    public static Card getZeroInYearFieldCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "0",
                "",
                faker.numerify("###"));
    }

    /* 16 */
    public static Card getExpiredCardCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 17 */
    public static Card getInvalidYearCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "99",
                "",
                faker.numerify("###"));
    }

    /* 18 */
    public static Card getCardholderOnlyFirstNameCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 19 */
    public static Card getLongCardholderNameCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "",
                "IGNAT SERGEEV-DROZDETSKIY",
                faker.numerify("###"));
    }

    /* 20 */
    public static Card getCyrillicCardholderNameCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 21 */
    public static Card getSpecialCharactersInCardholderNameCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 22 */
    public static Card getNumbersInCardholderNameCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "",
                "",
                faker.numerify("###"));
    }

    /* 23 */
    public static Card getOneNumberCVVCardInfo(){
        return new Card(approvedCard,
                generateMonth(),
                "",
                "",
                faker.numerify("#"));
    }

    /* 24 */
    public static Card getTwoNumbersCVVCardInfo(){
        return new Card(approvedCard,
                generateMonth(),
                "",
                "",
                faker.numerify("##"));
    }
}
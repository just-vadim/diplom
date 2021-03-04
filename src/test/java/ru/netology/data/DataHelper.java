package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.Year;
import java.util.Locale;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class DataHelper {
    private static Faker faker = new Faker();
    private static Faker fakerRu = new Faker(new Locale("ru"));

    private static String approvedCard = "4444 4444 4444 4441";
    private static String declinedCard = "4444 4444 4444 4442";

    private static String generateMonth() {
        Random random = new Random();
        int num = random.nextInt(12) + 1;
        if (num < 10) {
            return "0" + num;
        } else {
            return String.valueOf(num);
        }
    }

    private static String generateYear() {
        Random random = new Random();
        int fullYear = Year.now().getValue();
        String stringFullYear = String.valueOf(fullYear).substring(2,4);
        int year = parseInt(stringFullYear, 10) + random.nextInt(4) + 1;
        return String.valueOf(year);
    }

    /* 1 */
    public static Card getValidCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 2 done */
    public static Card getDeclinedCardInfo() {
        return new Card(declinedCard,
                generateMonth(),
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 3 */
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
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 5 */
    public static Card getEmptyMonthFieldCardInfo() {
        return new Card(approvedCard,
                "",
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 6 */
    public static Card getEmptyYearFiledCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "",
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 7 */
    public static Card getEmptyCardholderFiledCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                generateYear(),
                "",
                faker.numerify("###"));
    }

    /* 8 */
    public static Card getEmptyCVVFieldCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                "");
    }

    /* 9 */
    public static Card getInvalidCardNumberCardInfo() {
        return new Card("4276380043211234",
                generateMonth(),
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 10 */
    public static Card getNonexistentCardNumberCardInfo() {
        return new Card("1234432112344321",
                generateMonth(),
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 11 */
    public static Card getIncompleteCardNumberCardInfo() {
        return new Card("444444444444444",
                generateMonth(),
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 12 */
    public static Card getZerosInMonthFiledCardInfo() {
        return new Card(approvedCard,
                "00",
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 13 */
    public static Card getMothWithoutZeroFirstCharacterCardInfo() {
        return new Card(approvedCard,
                "5",
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 14 */
    public static Card getNonexistentMonthCardInfo() {
        return new Card(approvedCard,
                "68",
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 15 */
    public static Card getZeroInYearFieldCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "0",
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 16 */
    public static Card getExpiredCardCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "20",
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 17 */
    public static Card getInvalidYearCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                "99",
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 18 */
    public static Card getCardholderOnlyFirstNameCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                generateYear(),
                faker.name().firstName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 19 */
    public static Card getLongCardholderNameCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                generateYear(),
                "IGNAT SERGEEV-DROZDETSKIY",
                faker.numerify("###"));
    }

    /* 20 */
    public static Card getCyrillicCardholderNameCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                generateYear(),
                fakerRu.name().firstName().toUpperCase() + " " + fakerRu.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 21 */
    public static Card getSpecialCharactersInCardholderNameCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                generateYear(),
                faker.name().firstName().toUpperCase() + " ~!@#$%^&*()_+{}[]|\"?>< " + faker.name().lastName().toUpperCase(),
                faker.numerify("###"));
    }

    /* 22 */
    public static Card getNumbersInCardholderNameCardInfo() {
        return new Card(approvedCard,
                generateMonth(),
                generateYear(),
                faker.name().firstName().toUpperCase() + "12345" + " " + faker.name().lastName().toUpperCase() + "67890",
                faker.numerify("###"));
    }

    /* 23 */
    public static Card getOneNumberCVVCardInfo(){
        return new Card(approvedCard,
                generateMonth(),
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("#"));
    }

    /* 24 */
    public static Card getTwoNumbersCVVCardInfo(){
        return new Card(approvedCard,
                generateMonth(),
                generateYear(),
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                faker.numerify("##"));
    }
}
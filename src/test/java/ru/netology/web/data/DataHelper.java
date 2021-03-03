package ru.netology.web.data;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Getter
    @Setter
    private static int expectedFirstAccountBalance;

    @Getter
    @Setter
    private static int expectedSecondAccountBalance;

    @Getter
    @Setter
    private static int actualFirstAccountBalance;

    @Getter
    @Setter
    private static int actualSecondAccountBalance;

    public static void setActualBalance() {
        DataHelper.setExpectedFirstAccountBalance(actualFirstAccountBalance);
        DataHelper.setExpectedSecondAccountBalance(actualSecondAccountBalance);
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class DataForTransaction {
        private String account;
        private String amount;
    }

    public static DataForTransaction dataForRestoreFirstAccount() {
        int restoreAmount = (actualSecondAccountBalance - actualFirstAccountBalance) / 2;
        return new DataForTransaction("5559 0000 0000 0002", Integer.toString(restoreAmount));
    }

    public static DataForTransaction dataForRestoreSecondAccount() {
        int restoreAmount = (actualFirstAccountBalance - actualSecondAccountBalance) / 2;
        return new DataForTransaction("5559 0000 0000 0001", Integer.toString(restoreAmount));
    }

    public static DataForTransaction dataForTransferFrom2ndTo1st1() {
        expectedFirstAccountBalance += 1;
        expectedSecondAccountBalance -= 1;
        return new DataForTransaction("5559 0000 0000 0002", "1");
    }

    public static DataForTransaction dataForTransferFrom1stTo2nd1() {
        expectedFirstAccountBalance -= 1;
        expectedSecondAccountBalance += 1;
        return new DataForTransaction("5559 0000 0000 0001", "1");
    }

    public static DataForTransaction dataForTransferFrom2ndTo1st1500() {
        expectedFirstAccountBalance += 1500;
        expectedSecondAccountBalance -= 1500;
        return new DataForTransaction("5559 0000 0000 0002", "1500");
    }

    public static DataForTransaction dataForTransferFrom1stTo2nd1500() {
        expectedFirstAccountBalance -= 1500;
        expectedSecondAccountBalance += 1500;
        return new DataForTransaction("5559 0000 0000 0001", "1500");
    }

    public static DataForTransaction dataForTransferFrom2ndTo1stMax() {
        expectedFirstAccountBalance += actualSecondAccountBalance;
        expectedSecondAccountBalance -= actualSecondAccountBalance;
        return new DataForTransaction("5559 0000 0000 0002", Integer.toString(actualSecondAccountBalance));
    }

    public static DataForTransaction dataForTransferFrom1stTo2ndMax() {
        expectedFirstAccountBalance -= actualFirstAccountBalance;
        expectedSecondAccountBalance += actualFirstAccountBalance;
        return new DataForTransaction("5559 0000 0000 0001", Integer.toString(actualFirstAccountBalance));
    }

    public static DataForTransaction dataForTransferFrom2ndTo1stOverMax() {
        return new DataForTransaction("5559 0000 0000 0002",
                Integer.toString(actualSecondAccountBalance + 100));
    }

    public static DataForTransaction dataForTransferFrom1stTo2ndOverMax() {
        return new DataForTransaction("5559 0000 0000 0001",
                Integer.toString(actualFirstAccountBalance + 100));
    }

    public static DataForTransaction dataForTransferFrom1stTo1st1500() {
        return new DataForTransaction("5559 0000 0000 0001", "1500");
    }

    public static DataForTransaction dataForTransferFrom2ndTo2nd1500() {
        return new DataForTransaction("5559 0000 0000 0002", "1500");
    }
}

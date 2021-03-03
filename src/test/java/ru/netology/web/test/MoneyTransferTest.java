package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;

import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    void setUpSUT(DashboardPage dashboardPage) {
        if (DataHelper.getActualFirstAccountBalance() > DataHelper.getActualSecondAccountBalance()) {
            val dataForTransaction = DataHelper.dataForRestoreSecondAccount();
            dashboardPage.replenishSecondAccount().makeTransaction(dataForTransaction);
        } else {
            val dataForTransaction = DataHelper.dataForRestoreFirstAccount();
            dashboardPage.replenishFirstAccount().makeTransaction(dataForTransaction);
        }
    }

    @Test
    void shouldTransferFrom2ndTo1st1() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        if (DataHelper.getActualFirstAccountBalance() != DataHelper.getActualSecondAccountBalance())
            setUpSUT(dashboardPage);
        val replenishPage = dashboardPage.replenishFirstAccount();
        val dataForTransaction = DataHelper.dataForTransferFrom2ndTo1st1();
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        assertEquals(DataHelper.getExpectedFirstAccountBalance(), DataHelper.getActualFirstAccountBalance());
        assertEquals(DataHelper.getExpectedSecondAccountBalance(), DataHelper.getActualSecondAccountBalance());
    }

    @Test
    void shouldTransferFrom1stTo2nd1() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        if (DataHelper.getActualFirstAccountBalance() != DataHelper.getActualSecondAccountBalance())
            setUpSUT(dashboardPage);
        val replenishPage = dashboardPage.replenishSecondAccount();
        val dataForTransaction = DataHelper.dataForTransferFrom1stTo2nd1();
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        assertEquals(DataHelper.getExpectedFirstAccountBalance(), DataHelper.getActualFirstAccountBalance());
        assertEquals(DataHelper.getExpectedSecondAccountBalance(), DataHelper.getActualSecondAccountBalance());
    }

    @Test
    void shouldTransferFrom2ndTo1st1500() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        if (DataHelper.getActualFirstAccountBalance() != DataHelper.getActualSecondAccountBalance())
            setUpSUT(dashboardPage);
        val replenishPage = dashboardPage.replenishFirstAccount();
        val dataForTransaction = DataHelper.dataForTransferFrom2ndTo1st1500();
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        assertEquals(DataHelper.getExpectedFirstAccountBalance(), DataHelper.getActualFirstAccountBalance());
        assertEquals(DataHelper.getExpectedSecondAccountBalance(), DataHelper.getActualSecondAccountBalance());
    }

    @Test
    void shouldTransferFrom1stTo2nd1500() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        if (DataHelper.getActualFirstAccountBalance() != DataHelper.getActualSecondAccountBalance())
            setUpSUT(dashboardPage);
        val replenishPage = dashboardPage.replenishSecondAccount();
        val dataForTransaction = DataHelper.dataForTransferFrom1stTo2nd1500();
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        assertEquals(DataHelper.getExpectedFirstAccountBalance(), DataHelper.getActualFirstAccountBalance());
        assertEquals(DataHelper.getExpectedSecondAccountBalance(), DataHelper.getActualSecondAccountBalance());
    }

    @Test
    void shouldTransferFrom2ndTo1stMax() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        if (DataHelper.getActualFirstAccountBalance() != DataHelper.getActualSecondAccountBalance())
            setUpSUT(dashboardPage);
        val replenishPage = dashboardPage.replenishFirstAccount();
        val dataForTransaction = DataHelper.dataForTransferFrom2ndTo1stMax();
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        assertEquals(DataHelper.getExpectedFirstAccountBalance(), DataHelper.getActualFirstAccountBalance());
        assertEquals(DataHelper.getExpectedSecondAccountBalance(), DataHelper.getActualSecondAccountBalance());
    }

    @Test
    void shouldTransferFrom1stTo2ndMax() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        if (DataHelper.getActualFirstAccountBalance() != DataHelper.getActualSecondAccountBalance())
            setUpSUT(dashboardPage);
        val replenishPage = dashboardPage.replenishSecondAccount();
        val dataForTransaction = DataHelper.dataForTransferFrom1stTo2ndMax();
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        assertEquals(DataHelper.getExpectedFirstAccountBalance(), DataHelper.getActualFirstAccountBalance());
        assertEquals(DataHelper.getExpectedSecondAccountBalance(), DataHelper.getActualSecondAccountBalance());
    }

    @Test
    void shouldNotTransferFrom2ndTo1stOverMax() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        if (DataHelper.getActualFirstAccountBalance() != DataHelper.getActualSecondAccountBalance())
            setUpSUT(dashboardPage);
        val replenishPage = dashboardPage.replenishFirstAccount();
        val dataForTransaction = DataHelper.dataForTransferFrom2ndTo1stOverMax();
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        replenishPage.shouldHaveErrorMassage();
    }

    @Test
    void shouldNotTransferFrom1stTo2ndOverMax() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        if (DataHelper.getActualFirstAccountBalance() != DataHelper.getActualSecondAccountBalance())
            setUpSUT(dashboardPage);
        val replenishPage = dashboardPage.replenishSecondAccount();
        val dataForTransaction = DataHelper.dataForTransferFrom1stTo2ndOverMax();
        replenishPage.makeTransaction(dataForTransaction);
        replenishPage.shouldHaveErrorMassage();
    }

    @Test
    void shouldNotTransferFrom1stTo1st1500() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        if (DataHelper.getActualFirstAccountBalance() != DataHelper.getActualSecondAccountBalance())
            setUpSUT(dashboardPage);
        val replenishPage = dashboardPage.replenishFirstAccount();
        val dataForTransaction = DataHelper.dataForTransferFrom1stTo1st1500();
        replenishPage.makeTransaction(dataForTransaction);
        replenishPage.shouldHaveErrorMassage();
    }

    @Test
    void shouldNotTransferFrom2ndTo2nd1500() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.takeActualBalanceFromPage();
        DataHelper.setActualBalance();
        if (DataHelper.getActualFirstAccountBalance() != DataHelper.getActualSecondAccountBalance())
            setUpSUT(dashboardPage);
        val replenishPage = dashboardPage.replenishSecondAccount();
        val dataForTransaction = DataHelper.dataForTransferFrom2ndTo2nd1500();
        replenishPage.makeTransaction(dataForTransaction);
        replenishPage.shouldHaveErrorMassage();
    }
}


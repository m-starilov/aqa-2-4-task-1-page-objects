package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;

import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    private static DashboardPage dashboardPage;
    private static Integer balanceBeforeTransfer1st;
    private static Integer balanceBeforeTransfer2nd;

    void restoreAccounts() {
        val restoreAmount = DataHelper.getRestoreAmount();
        if (restoreAmount != 0) {
            val dataForTransaction = DataHelper.dataForRestoreAccounts(restoreAmount);
            if (restoreAmount > 0)
                dashboardPage.replenishSecondAccount().makeTransaction(dataForTransaction).takeActualBalanceFromPage();
            else dashboardPage.replenishFirstAccount().makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        }
    }

    @BeforeEach
    void setUp() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.takeActualBalanceFromPage();
        restoreAccounts();
        balanceBeforeTransfer1st = DataHelper.getActualFirstAccountBalance();
        balanceBeforeTransfer2nd = DataHelper.getActualSecondAccountBalance();
    }

    @Test
    void shouldTransferFrom2ndTo1st1() {
        val replenishPage = dashboardPage.replenishFirstAccount();
        val dataForTransaction = DataHelper
                .dataForTransfer("5559 0000 0000 0002", 1);
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        assertEquals(balanceBeforeTransfer2nd - 1, DataHelper.getActualSecondAccountBalance());
        assertEquals(balanceBeforeTransfer1st + 1, DataHelper.getActualFirstAccountBalance());
    }

    @Test
    void shouldTransferFrom1stTo2nd1() {
        val replenishPage = dashboardPage.replenishSecondAccount();
        val dataForTransaction = DataHelper
                .dataForTransfer("5559 0000 0000 0001",1);
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        assertEquals(balanceBeforeTransfer1st - 1, DataHelper.getActualFirstAccountBalance());
        assertEquals(balanceBeforeTransfer2nd + 1, DataHelper.getActualSecondAccountBalance());
    }

    @Test
    void shouldTransferFrom2ndTo1st1500() {
        val replenishPage = dashboardPage.replenishFirstAccount();
        val dataForTransaction = DataHelper
                .dataForTransfer("5559 0000 0000 0002",1500);
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        assertEquals(balanceBeforeTransfer2nd - 1500, DataHelper.getActualSecondAccountBalance());
        assertEquals(balanceBeforeTransfer1st + 1500, DataHelper.getActualFirstAccountBalance());
    }

    @Test
    void shouldTransferFrom1stTo2nd1500() {
        val replenishPage = dashboardPage.replenishSecondAccount();
        val dataForTransaction = DataHelper
                .dataForTransfer("5559 0000 0000 0001", 1500);
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        assertEquals(balanceBeforeTransfer1st - 1500, DataHelper.getActualFirstAccountBalance());
        assertEquals(balanceBeforeTransfer2nd + 1500, DataHelper.getActualSecondAccountBalance());
    }

    @Test
    void shouldTransferFrom2ndTo1stMax() {
        val replenishPage = dashboardPage.replenishFirstAccount();
        val dataForTransaction = DataHelper
                .dataForTransfer("5559 0000 0000 0002", balanceBeforeTransfer1st);
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        assertEquals(0, DataHelper.getActualSecondAccountBalance());
        assertEquals(balanceBeforeTransfer1st + balanceBeforeTransfer2nd,
                DataHelper.getActualFirstAccountBalance());
    }

    @Test
    void shouldTransferFrom1stTo2ndMax() {
        val replenishPage = dashboardPage.replenishSecondAccount();
        val dataForTransaction = DataHelper
                .dataForTransfer("5559 0000 0000 0001", balanceBeforeTransfer2nd);
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        assertEquals(0, DataHelper.getActualFirstAccountBalance());
        assertEquals(balanceBeforeTransfer2nd + balanceBeforeTransfer1st,
                DataHelper.getActualSecondAccountBalance());
    }

    @Test
    void shouldNotTransferFrom2ndTo1stOverMax() {
        val replenishPage = dashboardPage.replenishFirstAccount();
        val dataForTransaction = DataHelper
                .dataForTransfer("5559 0000 0000 0002", balanceBeforeTransfer2nd + 1);
        replenishPage.makeTransaction(dataForTransaction).takeActualBalanceFromPage();
        replenishPage.shouldHaveErrorMassage();
    }

    @Test
    void shouldNotTransferFrom1stTo2ndOverMax() {
        val replenishPage = dashboardPage.replenishSecondAccount();
        val dataForTransaction = DataHelper
                .dataForTransfer("5559 0000 0000 0001",balanceBeforeTransfer1st + 1);
        replenishPage.makeTransaction(dataForTransaction);
        replenishPage.shouldHaveErrorMassage();
    }

    @Test
    void shouldNotTransferFrom1stTo1st1500() {
        val replenishPage = dashboardPage.replenishFirstAccount();
        val dataForTransaction = DataHelper
                .dataForTransfer("5559 0000 0000 0001", 1500);
        replenishPage.makeTransaction(dataForTransaction);
        replenishPage.shouldHaveErrorMassage();
    }

    @Test
    void shouldNotTransferFrom2ndTo2nd1500() {
        val replenishPage = dashboardPage.replenishSecondAccount();
        val dataForTransaction = DataHelper
                .dataForTransfer("5559 0000 0000 0002", 1500);
        replenishPage.makeTransaction(dataForTransaction);
        replenishPage.shouldHaveErrorMassage();
    }
}


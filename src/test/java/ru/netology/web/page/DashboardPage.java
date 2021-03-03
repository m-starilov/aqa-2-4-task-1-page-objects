package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection accountList = $$("li.list__item");
    private ElementsCollection accountButtons = $$("[data-test-id=action-deposit]");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public void takeActualBalanceFromPage() {
        if (accountList.size() > 1) {
            val actual1st = Integer.parseInt(accountList.get(0).getText().split(" ")[5]);
            val actual2nd = Integer.parseInt(accountList.get(1).getText().split(" ")[5]);
            DataHelper.setActualFirstAccountBalance(actual1st);
            DataHelper.setActualSecondAccountBalance(actual2nd);
        }
    }

    public ReplenishPage replenishFirstAccount() {
        accountButtons.get(0).click();
        return new ReplenishPage();
    }

    public ReplenishPage replenishSecondAccount() {
        accountButtons.get(1).click();
        return new ReplenishPage();
    }
}

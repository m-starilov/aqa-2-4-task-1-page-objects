package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class ReplenishPage {
    private SelenideElement heading = $(byText("Пополнение карты"));
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement replenishButton = $(byText("Пополнить"));
    private SelenideElement errorMassage = $(byText("Ошибка!"));

    public ReplenishPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage makeTransaction(DataHelper.DataForTransaction dataForTransaction) {
        amountField.doubleClick().sendKeys(Keys.BACK_SPACE);
        amountField.setValue(dataForTransaction.getAmount());
        fromField.setValue(dataForTransaction.getAccount());
        replenishButton.click();
        return new DashboardPage();
    }

    public void shouldHaveErrorMassage() {
        errorMassage.shouldBe(visible);
    }
}

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
    private String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    public ReplenishPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage makeTransaction(DataHelper.DataForTransaction dataForTransaction) {
        amountField.setValue(deleteString).setValue(dataForTransaction.getAmount());
        fromField.setValue(deleteString).setValue(dataForTransaction.getAccountFrom());
        replenishButton.click();
        return new DashboardPage();
    }

    public void shouldHaveErrorMassage() {
        errorMassage.shouldBe(visible);
    }
}

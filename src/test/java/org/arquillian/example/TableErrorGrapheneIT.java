package org.arquillian.example;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@RunWith(Arquillian.class)
public class TableErrorGrapheneIT {

    @Drone
    private WebDriver browser;

    @Test
    @RunAsClient
    public void tableVarDataShouldNotBeErasedAfterSearch() {
        browser.get("http://localhost:8081/mojarra-bug-test-case/test.xhtml");

        WebElement contentTable = browser.findElement(By.id("firstForm:firstTable"));
        List<WebElement> tableCells = contentTable.findElements(By.xpath("//tr/td"));
        for (WebElement tableCell : tableCells) {
            assertNotEquals(tableCell.getText(), "--");
        }
    }

}

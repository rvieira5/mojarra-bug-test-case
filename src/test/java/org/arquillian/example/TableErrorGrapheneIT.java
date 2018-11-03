package org.arquillian.example;

import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.test.TestView;
import org.primefaces.test.pojo.TestObject;

@RunWith(Arquillian.class)
public class TableErrorGrapheneIT {
	private static final String WEBAPP_SRC = "src/main/webapp";

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "mojarra-bug-test-case.war")
				.addClasses(TestView.class, TestObject.class)
				.addAsWebResource(new File(WEBAPP_SRC, "test.xhtml"))
				.addAsWebResource(new File(WEBAPP_SRC, "WEB-INF/faces-config.xml"))
				.addAsWebResource(new File(WEBAPP_SRC, "WEB-INF/web.xml"));
	}

	@Drone
	private WebDriver browser;

	@FindBy(id = "firstForm:firstTable")
	private WebElement contentTable;

	@ArquillianResource
	private URL deploymentUrl;

	@Test
	public void tableVarDataShouldNotBeErasedAfterSearch() {
		browser.get(deploymentUrl.toExternalForm() + "test.xhtml");

		List<WebElement> tableCells = contentTable.findElements(By.xpath("//tr/td"));
		for (WebElement tableCell : tableCells) {
			assertNotEquals(tableCell.getText(), "--");
		}
	}

}

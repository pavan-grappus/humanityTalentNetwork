package runnerFiles;

import org.openqa.selenium.By;

import utility.GenericMethods;

public class addingFunction {

	public static void main(String[] args) {

		GenericMethods gm = new GenericMethods("E:\\eclipseWorkSpace\\HumanityHealth\\Driver\\chromedriver.exe",
				"E:\\eclipseWorkSpace\\HumanityHealth\\Reports\\", "asd.html");

		gm.startindebugmode("https://admin-dev.humanitytalent.net/dashboard/capabilities",
				"E:\\eclipseWorkSpace\\HumanityHealth\\Driver\\chromedriver.exe");

		gm.hold(10);

		for (int i = 0; i < 100; i++) {

			gm.click(By.xpath("//span[text()='Add New Question']"), "New Function button");

			gm.setText(By.xpath("//textarea[@placeholder='Start typing here…']"), "Question " + i, "name F" + i);

			gm.click(By.xpath("//span[text()='Confirm']"), "Confirm button");

			gm.waitforElementVisible(By
					.xpath("//div[@class='sc-bdnxRM jvCTkj']//button[@class ='MuiButtonBase-root MuiIconButton-root']"),
					30, "Sucess close");

			gm.click(By
					.xpath("//div[@class='sc-bdnxRM jvCTkj']//button[@class ='MuiButtonBase-root MuiIconButton-root']"),
					"Sucess close");

		}
	}

}

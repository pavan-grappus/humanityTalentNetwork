package PageObjects;

import org.openqa.selenium.By;

public class GeneralObjects {

	public By TabIcon = By.xpath("//head/link[@rel='icon']");

	public By TabName = By.xpath("//head/title");

	public By modalIcon = By.xpath("//div[@class='sc-eKaNGd cMApHi']");

	public By modalHeader = By.xpath("//div[@class='sc-fGgQJw iyajYx']/h2");

	public By modalSubText = By.xpath("//div[@class='sc-fGgQJw iyajYx']/p");

	public By modalCloseButton = By.xpath("//div[@class='sc-hmvkKb gsyRVd']/button");
}

package PageObjects;

import org.openqa.selenium.By;

public class WelcomeVideo {

	public By contentSection = By.xpath("//div[@class='content-section']");

	public By videoSection = By.xpath("//div[@id='video-section']");

	public By htnLogo = By.xpath("//img[@alt='hh-logo']");

	public By sponsorIntro = By.xpath("(//*[@id='sponsor-intro'])[1]");

	public By sponsorScrollUserName(Object order) {
		return By.xpath("(//div[@class='sponsor-about']/span[@class='user-name'])[" + order + "]");
	}

	public By sponsorScrollcompany(Object order) {
		return By.xpath("(//div[@class='sponsor-about']/span[@class='company-text'])[" + order + "]");
	}

	public By sponsorMessage = By.xpath("(//*[@id='sponsor-intro'])[2]");

	public By description = By.xpath("//p[@class='description']");

	public By qrHeader = By.xpath("(//p[@class='qr-code-title'])[1]");

	public By qrcodeImage = By.xpath("//div[@class='qr-code']/img");

	public By qrTitle = By.xpath("(//p[@class='qr-code-title'])[2]");

	public By iOSImage = By.xpath("//span[@class='qr-code-title']/img[1]");

	public By androidImage = By.xpath("//span[@class='qr-code-title']/img[2]");

	public By videoWrapper = By.xpath("//video[@id='video-wrapper']");
	
	public By errordesc = By.xpath("//p[@class='error-description']");
	
	public By joinNow_button = By.xpath("//button[@class='join-button']");

}

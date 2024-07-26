package Practice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Broken_links {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.shoppersstack.com");
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		for (WebElement link : links) {
			String href = link.getAttribute("href");
			
			if (href==null||href.isEmpty()) {
				System.out.println("Href Value is null");
				continue;
			}
			
			URL url=new URL(href);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.connect();
			int code = conn.getResponseCode();
			
			if (code>=400) {
				System.out.println(href+" Link Is Broken");
			}
			else {
				System.out.println(href+" Link Is Not Broken");
			}			
		}
		
		Thread.sleep(3000);
		driver.quit();
		
	}

}

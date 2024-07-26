package New_Topics;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Broken_Links {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://www.deadlinkcity.com/");
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		
		for (WebElement link : links) {
			String href = link.getAttribute("href");
			
			if (href==null||href.isEmpty()) {
				System.out.println("No Href Value");
				continue;
			}
			
			URL url=new URL(href);
			HttpURLConnection li = (HttpURLConnection) url.openConnection();
			li.connect();
			if (li.getResponseCode()>=400) {
				System.out.println(href+" Broken Link ");
			}
			else {
				System.out.println(href+" Not Broken Link ");
			}
		}
		
		Thread.sleep(3000);
		driver.quit();
		
	}

}

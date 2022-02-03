import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CrawlingWebToonDownload {

	public static void main(String[] args) throws IOException, InterruptedException {
//		String url = "https://tkor.gift/";
//		Document mainData = Jsoup.connect(url)
//				.timeout(30*1000)
//				.get();
//		
//		Thread.sleep(10*1000);
//		System.out.println("s");
////		for(int i=7; i<=8; i++) {
//			Elements menuList = mainData.select("#wt_list > div.dynamicContent > div > ul:nth-child(6)");
		
		
//		String url = "http://main.mujin.co.kr/";
//		System.setProperty("webdriver.chrome.driver", "S:/Development/Crawling/Selenium/chromedriver.exe");
		
		// 2. WebDriver 옵션 설정
//        ChromeOptions options = new ChromeOptions();
//	        options.addArguments("--start-maximized");          // 최대크기로
//        options.addArguments("--headless");                 // Browser를 띄우지 않음
//        options.addArguments("--disable-gpu");              // GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
//        options.addArguments("--no-sandbox");               // Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        
        
		// 브라우저 실행
////		WebDriver driver = new ChromeDriver(options);
//				
//		driver.get(url);
		
//		WebElement img = driver.findElement(By.cssSelector("#button_content2 > div > p > img"));
//		System.out.println(img.getAttribute("src"));
//		String imgurl = img.getAttribute("src");
		
		URL imgUrl = new URL("https://tkor.show/data/file/wtoon/16137521587412.jpeg");
        BufferedImage jpg = ImageIO.read(imgUrl);
        File file = new File("S:\\CrawlingTest\\a.jpg");
        ImageIO.write(jpg, "jpg", file);
		
		
//		driver.quit();
	}
}

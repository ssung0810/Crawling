package CoinStatus;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class getCoin {
	
	public void payCoin() throws IOException, InterruptedException {
		String url = "https://upbit.com/exchange?code=CRIX.UPBIT.KRW-PCI";
//		System.setProperty("webdriver.chrome.driver", "S:/Development/Crawling/Selenium/chromedriver.exe");
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36 Edg/88.0.705.81";
//		
//		// 2. WebDriver 옵션 설정
//        ChromeOptions options = new ChromeOptions();
////	        options.addArguments("--start-maximized");          // 최대크기로
////        options.addArguments("--headless");                 // Browser를 띄우지 않음
//        options.addArguments("--disable-gpu");              // GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
//        options.addArguments("--no-sandbox");               // Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
//        options.addArguments("user-agent="+userAgent);
//		
//        // 브라우저 실행
//  		WebDriver driver = new ChromeDriver(options);
//  		
//  		driver.get(url);
//		
//  		Thread.sleep(100000);
//  		
//		String txt = driver.findElement(By.xpath("//*[@id='UpbitLayout']/div[3]/div/section[1]/article[1]/div/span[1]/div[1]/span[1]/strong")).getText();
//  		System.out.println(txt);
  		
  		
		
		System.out.println("시작");
		Connection.Response response = Jsoup.connect(url)
										.userAgent(userAgent)
										.timeout(3000)
										.header("Referer", "https://upbit.com/")
										.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
										.header("Accept-Encoding", "gzip, deflate, br")
										.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
										.header("Path", "/exchange?code=CRIX.UPBIT.KRW-PCI")
										.header("Sec-Fetch-Dest", "document")
										.header("Sec-Fetch-Mode", "navigate")
										.header("Sec-Fetch-Site", "same-origin")
										.header("Sec-Fetch-User", "?1")
										.method(Connection.Method.GET)
										.execute();
		
		Thread.sleep(5000);
		
		System.out.println(response.statusCode());
		Document coinDom = response.parse();
		
		String txt = coinDom.select("#UpbitLayout > div:nth-child(4) > div > section.ty01 > article:nth-child(1) > div > span.marketB > div.down.ty01 > span.first > strong").text();
		
		System.out.println(txt);
		System.out.println("종료");
	}
}

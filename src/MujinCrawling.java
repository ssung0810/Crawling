import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MujinCrawling {
	
	public static void main(String[] args) throws IOException {
		String url = "http://main.mujin.co.kr/Account/";
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";
		OutputStream output = new FileOutputStream("D:/Crawling.md");
		Map<String, String> data = new HashMap<>();
		data.put("login_gubun", "");
		data.put("user_name", "190211c");
		data.put("password", "donghwa93");
		
		Connection.Response response = Jsoup.connect(url)
										.userAgent(userAgent)
										.timeout(3000)
										.header("Referer", "http://main.mujin.co.kr/Account/")
										.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
										.header("Accept-Encoding", "gzip, deflate")
										.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
										.header("Content-Type", "application/x-www-form-urlencoded")
										.data(data)
										.method(Connection.Method.GET)
										.execute();
		
		Map<String, String> loginCookie = response.cookies();
		
		Connection.Response TnA = Jsoup.connect("https://mujingw.mujin.co.kr:8443/mujin/")
						.userAgent(userAgent)
						.timeout(3000)
						.header("Referer", "http://main.mujin.co.kr/Account/")
						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
						.header("Accept-Encoding", "gzip, deflate")
						.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
						.header("Content-Type", "application/x-www-form-urlencoded")
						.cookies(loginCookie)
						.execute();
//						.get();
		System.out.println(TnA.statusCode());
		
//		Document TnAMain = TnA.parse();
//		
//		Elements names = TnAMain.select("ul.txt01 > li");
//		for(Element name : names) {
//			System.out.println(name.text());
//		}
//		
//		String TnAMainHTML = TnAMain.text();
//		
//		byte[] by = TnAMainHTML.getBytes();
//		output.write(by);
	}

}

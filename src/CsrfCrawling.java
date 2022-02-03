import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CsrfCrawling {
	String ID;
	String PW;
	
	public CsrfCrawling(String ID, String PW) {
		this.ID=ID;
		this.PW=PW;
	}
	
	public String Crawl() throws IOException {
		String url = "http://main.mujin.co.kr/Account/";
		
		Connection.Response initial = Jsoup.connect(url)
										.method(Connection.Method.GET)
										.execute();
		Document key = initial.parse();
		String csrf = initial.cookies().get("csrftoken");
		
		Connection.Response login = Jsoup.connect("login page url")
									.cookies(initial.cookies())
									.data("id", ID, "PW", PW)
									.data("auto", "false", "csrftoken", csrf)
									.method(Connection.Method.POST)
									.timeout(5000)
									.execute();
		
		Document doc = Jsoup.connect("")
						.cookies(login.cookies())
						.timeout(3000000).get();
		
		Elements elem = doc.select("");
		
		for(Element el : elem.select("")) {
			System.out.println(el.text());
		}
		
		return "Success";
	}

	public static void main(String[] args) {
		String URL = "https://www.naver.com/";
		Document doc = null;
		Elements elms = new Elements();
		
		try {
			// Connection 생성
			Connection conn = Jsoup.connect(URL)
								.userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
								.header("Accept", "text/html")
					            .header("Accept-Encoding", "gzip,deflate")
					            .header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
					            .header("Connection", "keep-alive")
					            .ignoreContentType(true)
					            .timeout(30000);
								
			// HTML 파싱
			doc = conn.get();
			elms = doc.select(".special_logo_link");
			System.out.println(elms.attr("src"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}

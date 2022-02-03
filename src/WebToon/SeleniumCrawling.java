package WebToon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class SeleniumCrawling {

	public static void main(String[] args) {
		long beforeTime = System.currentTimeMillis();

		getWebToonMethod getTitle = new getWebToonMethod();
		String chrome = "webdriver.chrome.driver";
		String driverLocation = "C:/Users/ogd00/Desktop/Development/Crawling/Selenium/chromedriver.exe";
		
		
		/********* 지정한 제목의 이름과 링크 가져오기 **********/
		// 파일에 저장되있는 제목가져오기
//		ArrayList<String> box = new ArrayList<String>();
//		try{
//			File file = new File("C:\\Users\\ogd00\\Desktop\\CrawlingTest\\WebToon\\setTitle.txt");
//			FileReader fileReader = new FileReader(file);
//			BufferedReader bfr = new BufferedReader(fileReader);
//			String txt = "";
//			while((txt = bfr.readLine()) != null) {
//				box.add(txt);
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("파일에서 제목가져오기 실패");
//		}
//		
//		// 파일에서 가져온 제목들의 제목과 링크를 새로운 파일에 저장
//		try{
//			System.out.println("웹툰 제목과 링크가져오기 시작");
//			String saveURL = "C:\\Users\\ogd00\\Desktop\\CrawlingTest\\WebToon\\setTitleURL.txt";
//			String getURL = "https://tkor.gift/";
//			// getTitleURL(제목리스트, 저장할 위치, 가져올 주소, 브라우저종류, 드라이버위치)
//			getTitle.getTitleURL(box, saveURL, getURL, chrome, driverLocation);			
//		} catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("실패했습니다");
//		} finally {
//			System.out.println("웹툰 제목과 링크가져오기 종료");
//		}
		/********* 지정한 제목의 이름과 링크 가져오기 종료 **********/
		
		
		/********* 지정한 제목의 가장 최근 목록 가져오기 **********/
		ArrayList<String> TitleList = new ArrayList<String>();
		try {
			File file = new File("C:\\Users\\ogd00\\Desktop\\CrawlingTest\\WebToon\\setTitleURL.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fileReader);
			String URL = "";
			while((URL = bfr.readLine()) != null) {
				TitleList.add(URL.split("-_-")[1]);
			}
			getTitle.getStandardToon(TitleList, chrome, driverLocation);
		} catch(Exception e) {
			e.printStackTrace();
		}
		/********* 지정한 제목의 가장 최근 목록 가져오기 종료 **********/
		
		
		/********* 만화별 이미지파일 PDF로 저장하기 **********/
		ArrayList<String> URLbox = new ArrayList<String>();
		ArrayList<String> standardList = new ArrayList<String>();
		try {
			// 웹툰 별 링크들 저장하기
			File file = new File("C:\\Users\\ogd00\\Desktop\\CrawlingTest\\WebToon\\setTitleURL.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fileReader);
			String URL = "";
			while((URL = bfr.readLine()) != null) {
				URLbox.add(URL.split("-_-")[1]);
			}
			
			// 웹툰 별 기준이 되는 최근꺼 저장
			File standardFile = new File("C:\\Users\\ogd00\\Desktop\\CrawlingTest\\WebToon\\toonStandard.txt");
			fileReader = new FileReader(standardFile);
			bfr = new BufferedReader(fileReader);
			URL = "";
			while((URL = bfr.readLine()) != null) {
				standardList.add(URL);
			}
			
			getTitle.getImg(URLbox, standardList, chrome, driverLocation);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("이미지파일 저장에 실패했습니다");
		}
		/********* 만화별 이미지파일 PDF로 저장하기 **********/
		
		
		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
		System.out.println("시간차이(m) : "+secDiffTime);
	}
}

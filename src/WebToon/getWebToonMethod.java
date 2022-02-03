package WebToon;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class getWebToonMethod {
	
	// 지정한 웹툰의 제목과 링크를 파일에 저장하기
	public void getTitleURL(ArrayList<String> box, String saveURL, String getURL, String browser, String driverLocation) throws Exception {
		File writeFile = new File(saveURL);
		BufferedWriter bfw = new BufferedWriter(new FileWriter(writeFile));
		
		System.setProperty(browser, driverLocation);
		ArrayList<Map<String, String>> titleBox = new ArrayList<Map<String, String>>();
		Map<String, String> miniBox = new HashMap<String, String>();
		
		// 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
//	        options.addArguments("--start-maximized");          // 최대크기로
        options.addArguments("--headless");                 // Browser를 띄우지 않음
        options.addArguments("--disable-gpu");              // GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        options.addArguments("--no-sandbox");               // Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        
        
		// 브라우저 실행
		WebDriver driver = new ChromeDriver(options);
				
		driver.get(getURL);
		
		// 지정한 페이지에서 전체 제목가져오기
		List<WebElement> titleLi = driver.findElements(By.cssSelector("#wt_list > div.dynamicContent > div > ul"));
        for(WebElement title : titleLi ) {
        	List<WebElement> a = title.findElements(By.cssSelector("li.section-item"));
        	for(WebElement b : a) {
        		String getTitle = b.getAttribute("alt");
        		if(box.contains(getTitle)) {
        			miniBox = new HashMap<String, String>();
        			String getTitleURL = b.findElement(By.cssSelector("a.toon-more")).getAttribute("href");
            		miniBox.put("data", getTitle+"-_-"+getTitleURL);
            		titleBox.add(miniBox);
        		}
        	}
        }
		
        // 저장한 제목들을 링크와같이 파일에 저장하기
        if(titleBox.size() > 0) {
	        for(int i=0; i<titleBox.size(); i++) {
	        	String folderTitle = titleBox.get(i).get("data").split("-_-")[0];
	        	File folder = new File("C:\\Users\\ogd00\\Desktop\\CrawlingTest\\WebToon\\"+folderTitle);
	        	if(!folder.exists()) folder.mkdir();
	        	
	        	String result = titleBox.get(i).get("data");
	        	bfw.write(result);
	        	bfw.newLine();
	        }
        }
        
        bfw.close();
		driver.quit();
	}

	
	// 웹툰별 가장 최근 제목과 날짜 저장하기
	public void getStandardToon(ArrayList<String> url, String browser, String driverLocation) throws IOException {
		System.setProperty(browser, driverLocation);
		
		// 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
//	    	options.addArguments("--start-maximized");          // 최대크기로
//      	options.addArguments("--headless");                 // Browser를 띄우지 않음
        options.addArguments("--disable-gpu");              // GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        options.addArguments("--no-sandbox");               // Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
 		
        File writeFile = new File("C:\\Users\\ogd00\\Desktop\\CrawlingTest\\WebToon\\toonStandard.txt");
        BufferedWriter bfw = new BufferedWriter(new FileWriter(writeFile));
        ArrayList<String> saveBox = new ArrayList<String>();
        WebDriver driver = new ChromeDriver(options);
        
        
        try{
			// 여러개의 만화URL들을 처음부터 끝까지 driver에 입력해서 따로 실행시키기 
			for(String titleURL : url) {
				driver.get(titleURL);
				
				WebElement titleList = driver.findElement(By.cssSelector("#fboardlist > table > tbody > tr:nth-child(1)"));
				String title = titleList.findElement(By.cssSelector("td:nth-child(2)")).getText();
				String date = titleList.findElement(By.cssSelector("td:nth-child(3)")).getText();
				String saveText = title+"::"+date;
				bfw.write(saveText);
	        	bfw.newLine();
	        	System.out.println(title+" 저장");
			}
			
			bfw.close();
			System.out.println("저장이 완료되었습니다");
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        driver.quit();
	}
	
	
	// 저장한 링크별 이미지 가져오기
	public void getImg(ArrayList<String> url, ArrayList<String> standardList, String browser, String driverLocation) throws IOException {
		System.setProperty(browser, driverLocation);
		ArrayList<Map<String, String>> titleBox = new ArrayList<Map<String, String>>();
		Map<String, String> miniBox = new HashMap<String, String>();
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36";
		
		// 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
//	    	options.addArguments("--start-maximized");          // 최대크기로
      	options.addArguments("--headless");                 // Browser를 띄우지 않음
        options.addArguments("--disable-gpu");              // GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        options.addArguments("--no-sandbox");               // Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
 		
        File writeFile = new File("C:\\Users\\ogd00\\Desktop\\CrawlingTest\\WebToon\\toonStandard.txt");
        BufferedWriter bfw = new BufferedWriter(new FileWriter(writeFile));
        WebDriver driver = new ChromeDriver(options);;
        WebDriver ToonDriver = new ChromeDriver(options);;
        int standardCnt = 0;
        int toonDriverCk = 0;
		// 파일 주소 열기
		try{
			// 여러개의 만화URL들을 처음부터 끝까지 driver에 입력해서 따로 실행시키기 
			for(String titleURL : url) {
				driver.get(titleURL);
				
				int saveCnt = 0;
				String standardText = standardList.get(standardCnt);
				String ToonTitle = driver.findElement(By.cssSelector("#containerCol > table.bt_view1 > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(1) > td")).getText();		// 만화제목 가져오기
				String saveLocation = "C:\\Users\\ogd00\\Desktop\\CrawlingTest\\WebToon\\"+ToonTitle;		// 만화 이름 폴더 지정
				// 만화 링크를 담고있는 tr태그 저장
				List<WebElement> URLList = driver.findElements(By.cssSelector("#fboardlist > table > tbody > tr"));
				
				System.out.println(ToonTitle + " 작업중..");
				// 태그 하나씩 살피며 접근
				for(WebElement a : URLList) {
					String linkTitle = a.findElement(By.cssSelector("td:nth-child(2)")).getText();
					String linkDate = a.findElement(By.cssSelector("td:nth-child(3)")).getText();
					String saveText = linkTitle+"::"+linkDate;
					// 웹툰의 첫 번째 링크에서만 '이름::날짜' 형식으로 업데이트
					if(saveCnt == 0) {
						bfw.write(saveText);
			        	bfw.newLine();
			        	saveCnt++;
					}
					// 체크한 링크와 파일의 이름이 동일하면 건너뛰기
					if(standardText.equals(saveText)) break;
					
					toonDriverCk++;
					String link = "https://tkor.show"+a.findElement(By.cssSelector("td:nth-child(1)")).getAttribute("data-role");
					
					ToonDriver.get(link);
					List<WebElement> imgLink = ToonDriver.findElements(By.cssSelector("#toon_img > img"));
					
					String finalLocation = saveLocation+"\\"+linkTitle;
					int cnt = 1;
					
//					PDDocument doc = new PDDocument();
					// 링크 하나씩 타면서 이미지 PDF로 저장
					for(WebElement img : imgLink) {
//						if(cnt == 2) break;
						String fileNm = cnt+".jpg";
						
						// 저장할려는 웹툰화수에 맞는 폴더 생성(ex. 나노마신 10화)
						File saveToonFile = new File(finalLocation);
						if(!saveToonFile.exists()) saveToonFile.mkdir();
						
						// 이미지 링크 가져오기
						String getImg = img.getAttribute("src");
						/* 
						 * 이미지를 그냥 URL로 넣어서 했을 때 403에러가 나옴
						 * 서버에서 User-Agent를 크롬이 아닌 자바로 인식하면서
						 * 브라우저가 아닌 봇의 접근으로 인식하여 차단해버림
						 * 그래서 User-Agent를 직접 지정하여 연결해줌 
						 */
						URL imgUrl = new URL(getImg);
						HttpURLConnection conn = (HttpURLConnection) imgUrl
													.openConnection();
						conn.setRequestProperty("User-Agent", userAgent);
						
				        BufferedImage jpg = ImageIO.read(conn.getInputStream());
				        File file = new File(finalLocation+"\\"+fileNm);
				        
				        ImageIO.write(jpg, "jpg", file);
//				        PDPage page = new PDPage();
//			            doc.addPage(page);
//			            
//			            PDImageXObject pdImage = PDImageXObject.createFromFile(getImg, doc);
//			            PDPageContentStream contents = new PDPageContentStream(doc, page);
//			            contents.drawImage(pdImage, 0, 0, 612, 796);
//			             
//			            contents.close();
//			            doc.save(finalLocation+"\\"+linkTitle+".pdf");
				        
				        cnt++;
					}
//					System.out.println(linkTitle+" pdf변환 시작");
					makePDF(finalLocation+"\\", finalLocation+"\\"+linkTitle+".pdf");
					System.out.println(linkTitle+" 저장됐습니다");
				}
				
				standardCnt++;
			}
		} catch(Exception e) {
			System.out.println("이미지 저장 실패");
			e.printStackTrace();
		} finally {
			driver.quit();
			if(toonDriverCk > 0) ToonDriver.quit();
			bfw.close();
		}
	}
	
	public void makePDF(String imagePath, String pdfPath) {
        PDDocument doc = new PDDocument();
        try {
        	File imgDir = new File(imagePath);
        	File[] imgFiles = imgDir.listFiles();
        	for(int i=1; i<=imgFiles.length; i++) {
	            PDPage page = new PDPage();
	            doc.addPage(page);
	            
	            PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath+i+".jpg", doc);
	            PDPageContentStream contents = new PDPageContentStream(doc, page);
				//drawImage(이미지 객체, x좌표, y좌표, 너비, 높이)
	            contents.drawImage(pdImage, 0, 0, 612, 796);
	             
	            contents.close();
	            doc.save(pdfPath);
        	}
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
            try {
				doc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            System.out.println("");
            System.out.println("fin");
        }
	}
}

package passwordModify;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 드라이버 종류와 위치 지정
        String chrome = "webdriver.chrome.driver";
        String driverLocation = "C:/Users/ogd00/Desktop/Development/Crawling/Selenium/chromedriver.exe";
        ModifyBody md = new ModifyBody();

        // md.Naver(아이디, 비밀번호, 핸드폰번호, 드라이버종류, 드라이버위치)
        String id = "ogd000os";
        String pw = "tkcjS10*";
        String phone = "01092146877";		// '-'제외하고 숫자만 입력
        md.Naver(id, pw, phone, chrome, driverLocation);
    }
}

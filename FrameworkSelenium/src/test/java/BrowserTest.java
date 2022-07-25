import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    String projectPath = System.getProperty("user.dir");
	    System.out.println("Project Path is"+projectPath);
	    
	    System.setProperty("webdriver.chrome.driver",projectPath +"/lib/chromeDriver/chromedriver");
	    
		//System.setProperty("webdriver.chrome.driver","/Users/divyaupadhyay/eclipse-personal/FrameworkSelenium/lib/chromeDriver/chromedriver");
		WebDriver driver = new ChromeDriver();
	
		driver.get("https://www.selenium.dev/");
		driver.manage().window().maximize();
		
		//driver.quit();

	}

}

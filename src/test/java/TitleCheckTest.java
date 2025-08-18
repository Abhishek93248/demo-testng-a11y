import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.browserstack.accessibility.AccessibilityUtils;

public class TitleCheckTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testWebsiteTitle() {
        driver.get("https://www.browserstack.com");
        String expectedTitle = "Most Reliable App & Cross Browser Testing Platform |BrowserStack";
        String actualTitle = driver.getTitle();
        System.out.println("Page Title: " + actualTitle);
    

       Map<String, Object> summary = AccessibilityUtils.getResultsSummary(driver);


// ✅ Extract total issue count safely
int totalIssueCount = ((Number) summary.getOrDefault("totalIssueCount", 0)).intValue();

System.out.println("Total issue count: " + totalIssueCount);

// ✅ Assert threshold
Assert.assertTrue(totalIssueCount < 12, 
    "Total issue count breached the threshold! Found: " + totalIssueCount);
// int criticalIssueCount = Integer.parseInt(String.valueOf(((Map)summary.get("issueCountBySeverity")).get("critical")));
// System.out.println("count: " + criticalIssueCount);
// Assert.assertTrue(criticalIssueCount < 10, "Critical issue count breached the threshold!");

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

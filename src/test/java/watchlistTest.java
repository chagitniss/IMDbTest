import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataProvider.ConfigFileReader;

public class watchlistTest {
	
	public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 20);
    
	private static String appURL = "https://www.imdb.com/?ref_=nv_home";

    
	private static EventFiringWebDriver e_driver;
    private static WebEventListener eventListener;
    
    private Double ratingEqualsOrHigher = 9.2;
    private static List<String> addedToWatchList = new ArrayList<String>();
    
    static ConfigFileReader configFileReader = new ConfigFileReader();
		
    
    @BeforeClass
    public static void setUp()
    {

    	 System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
	     driver = new ChromeDriver();
	     driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
	     	     
	     e_driver = new EventFiringWebDriver(driver);
	     eventListener = new WebEventListener();
	     e_driver.register(eventListener);
	     
	     driver.get(appURL);
	     
	     
	     
    }
    
    /*
     * Test1() check that each of the shows with rating equals or higher can be added to watchList 
     */
    
    @Test
    public void Test1() throws Exception
    {
    	
    	//Login
    	
    	login(configFileReader.getEmail(), configFileReader.getPassword());
    	
    	//Enter the list of TV series available on the site
    	
    	driver.findElement(By.xpath("//*[@id=\"navTitleMenu\"]/p/a[2]")).click();
    	
    	//Clicking '+' button of any item whose rating is greater than or equal to ratingEqualsOrHigher
    	//and add it to addedToWatchList
    	
    	List<WebElement> TVSeries = e_driver.findElements(By.xpath("//*[@class='lister-list']/tr"));
    	
    	for(WebElement w: TVSeries) {
    		if(Double.parseDouble(w.findElement(By.tagName("strong")).getText()) >= ratingEqualsOrHigher) {
    			addedToWatchList.add(w.findElement(By.xpath(".//td[@class='titleColumn']/a")).getText());
    			try {
    				w.findElement(By.xpath(".//div[@title='Click to add to watchlist']")).click();

    		    } catch (NoSuchElementException e) {}
    		}
    	}
    }	
    
    /*
     * Test2() check that each of the shows actually appears in the watchList
     */
    
    @Test
    public void Test2()
    {    	
    	//Enter the WatchList page
    	
    	driver.findElement(By.xpath("//*[@id=\"navWatchlistMenu\"]/p/a")).click();
    	List<WebElement> watchlist = driver.findElements(By.xpath("//*[@id='center-1-react']/div/div[3]/div/div"));
    	
    	
    	//Comparing the added items list along the existing list in watchList page
    	
    	assertEquals(watchlist.size(), addedToWatchList.size());
    	
    	//Initializes a set of the list on a watchList page, 
    	//so that checking each item - whether it exists - does not take too many time complexity
    	
    	Set<String> SetWatchList = new HashSet<String>();
    	
    	for (WebElement x : watchlist) {
    		SetWatchList.add(x.findElement(By.xpath(".//h3[@class='lister-item-header']/a")).getText());
    	} 
    	
    	//Check if each and every item that was supposed to be added, was actually added
    	
    	for (String s: addedToWatchList) {
    		assertTrue(SetWatchList.contains(s));
    	}
    }
    
    
    @AfterClass
    public static void coseBrowser()
    {  
        driver.quit();
    }
    
    /*
     * login the IMDb site
     */
    
    private void login(String userName, String password) {
    	driver.findElement(By.id("imdb-signin-link")).click();
    	driver.findElement(By.xpath("//*[@id=\"signin-options\"]/div/div[1]/a[1]\r\n")).click();
    	driver.findElement(By.name("email")).sendKeys(userName);
    	driver.findElement(By.name("password")).sendKeys(password);
    	driver.findElement(By.id("signInSubmit")).click();
    }
}

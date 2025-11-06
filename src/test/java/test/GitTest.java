public class FirstTest{

	public static void main(String[] args){
		WebDriver driver = new ChromeDriver();
		List<WebElements> sugesstions = driver.findElements(By.xpath("//div[@class='wM6W7d']/span[normalize-space()]"));
		sugesstions[2].click();
		for(int i = 0; i<sugesstions.size(); i++){
			System.out.println("All items are : " + sugesstions.get(i).getText());
		}
	}
}
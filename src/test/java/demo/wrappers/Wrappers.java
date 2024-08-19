package demo.wrappers;

import java.util.*;
import java.text.NumberFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    public static void StoreTitleAndDiscount(WebDriver driver, List<WebElement> discounts) {
        try {
            List<String> titleresult = new ArrayList<>();
            List<Integer> discountResult = new ArrayList<>();
            for (WebElement discount : discounts) {
                String dicountvalue = discount.getText().replaceAll("[^0-9]", "");
                if (Integer.parseInt(dicountvalue) > 17) {
                    titleresult.add(driver.findElement(By.xpath(
                            "//div[@class='yKfJKb row']/div[2]/div/div/div[3]/span/../../../../../div/div[@class='KzDlHZ']"))
                            .getText());
                    discountResult.add(Integer.parseInt(dicountvalue));
                }
            }
            // Printing the title and discount value of product whose dicount > 17
            for (String title : titleresult) {
                for (Integer discountValue : discountResult) {
                    System.out.println("The title of product is " + title + " and discount value" + discountValue);
                }
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void captureTheProductHavingRatingLessthanOrEqualToFour(List<WebElement> ratings, WebDriver driver) {
        try {
            int count = 0;
            for (WebElement rating : ratings) {
                String ratingInString = rating.getText();
                // System.out.println(ratingInString);
                double ratingValue = Double.parseDouble(ratingInString);
                if (ratingValue <= 4.0) {
                    count++;
                }
            }
            System.out.println("the count of items with rating less than or equal to 4stars:" + count);
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enterText(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickOnElement(ChromeDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectCheckBox(ChromeDriver driver, String checkBoxText) {
        try {
            WebElement element = driver.findElement(
                    By.xpath("//div[contains(@class, '_6i1qKy') and contains(text(),'4')]/../div[@class='XqNaEv']"));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void topFiveRatedProduct(WebDriver driver, List<WebElement> viewList) {
        try {
            List<Integer> viewListInInteger = new ArrayList<>();
            for (int i = 0; i < viewList.size(); i++) {
                viewListInInteger.add(Integer.parseInt(viewList.get(i).getText().replaceAll("[^0-9]", "")));
            }
            // reverseOrder() method to sort in descending order
            Collections.sort(viewListInInteger, Collections.reverseOrder());

            int count = 0;
            for (int i = 0; i <= viewListInInteger.size(); i++) {
                if (count < 5) {
                    count++;
                    int ValueAtIndex = viewListInInteger.get(i);
                    NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
                    String formattedNumber = nf.format(ValueAtIndex);
                    String productTitle = driver.findElement(
                            By.xpath("//div[contains(@class,'afFzxY')]/span[@class='Wphh3N' and contains(text(),'"
                                    + formattedNumber
                                    + "')]/../..//a[@class='wjcEIp']"))
                            .getText();
                    String imageURL = driver.findElement(By.xpath(
                            "//div[contains(@class,'afFzxY')]/span[@class='Wphh3N' and contains(text(),'"
                                    + formattedNumber
                                    + "')]/../..//a[@class='wjcEIp']/../..//div[@class='_4WELSP']/img"))
                            .getAttribute("src");

                    System.out.println(viewListInInteger.get(i) + " = " + productTitle + " , " + imageURL);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

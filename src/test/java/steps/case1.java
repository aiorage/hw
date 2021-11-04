package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.Selenide.close;
import static java.lang.Thread.sleep;
public class case1 {

    public static ChromeDriver driver;

    @Before
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://Users//HP//Downloads//chromedriver_win32//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public static void tearDown() {
        driver.quit();
        close();
    }

    @Дано("Вход в бсл через (.*) с паролем (.*)")
    public void вход_в_бсл_через_с_паролем(String login, String password) throws InterruptedException {
        driver.get("https://sso.kz00c1.kz.infra/opensso/UI/Login?goto=https%3A%2F%2Fbsl.kz00c1.kz.infra%2Fbsl%2F");
        sleep(2000);
        driver.findElementById("IDToken1").sendKeys(login);
        sleep(2000);
        driver.findElementById("IDToken2").sendKeys(password);
        sleep(2000);
        driver.findElementById("kc-login").click();
    }

    @Тогда("Нажать на создать банк")
    public void нажать_на_создать_банк() throws InterruptedException {
        driver.findElementByXPath("/html/body/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/a/div").click();
        sleep(2000);
    }

    @Дано("Код банка (.*), Второстепенный банковский код (.*), Название банка (.*), статус, Сдвиг даты платежа")
    public void код_банка_Второстепенный_банковский_код_Название_банка_статус_Сдвиг_даты_платежа(String bankcode, String secbankcode, String bankname) {
        driver.findElementById("id24").sendKeys(bankcode);
        driver.findElementById("id25").sendKeys(secbankcode);
        driver.findElementById("id26").sendKeys(bankname);
        Select stat = new Select(driver.findElement(By.name("formContent:status")));
        stat.selectByVisibleText("Активный");
        driver.findElementById("id29").sendKeys("1");
        driver.findElementById("id15").click();

    }

    @Тогда("Проверка создания банка")
    public void проверка_создания_банка() throws InterruptedException {
        String chek = driver.findElementByXPath("/html/body/div[2]/div[1]/div[1]/div/div[1]/ul[1]/li[1]/span[2]/span").getText();
        Assert.assertNotNull(chek, "bank name is null");
        sleep(2000);


    }


}

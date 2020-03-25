import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Ceshi {
    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String nodeURL, String browser) throws  MalformedURLException {
        DesiredCapabilities desiredCapabilities;
//        判断要打开的浏览器
        MutableCapabilities options = null;
        if (browser .equals("chrome")) {
            options = new ChromeOptions();//获取chrome浏览器的配置工具
            options.setCapability("browser name", "chrome");//设置浏览器的名称(chrome)
            options.setCapability("platform", Platform.LINUX);//设置浏览器的版本（WIN版）
        } else if (browser .equals( "firefox")) {
            options = new FirefoxOptions();//获取chrome浏览器的配置工具
            options.setCapability("browser name", "firefox");//设置浏览器的名称(chrome)
            options.setCapability("platform", Platform.LINUX);//设置浏览器的版本（WIN版）
        } else {
            desiredCapabilities = DesiredCapabilities.chrome();
        }

        String url = "http://192.168.43.54:4444" + "/wd/hub";
        WebDriver driver = new RemoteWebDriver(new URL(url), options);
//        打开百度
        driver.get("http://www.baidu.com");
        System.out.println(browser + driver.getTitle());
//        关闭浏览器
        driver.quit();

    }


    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                arguments("http://192.168.43.54:5902", "chrome"),
                arguments("http://192.168.43.54:5901", "firefox")
                //arguments("http://192.168.43.245:5555", "fix")
        );

    }
}

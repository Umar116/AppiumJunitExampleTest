
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

open class AppiumSetup {

    @BeforeEach
    fun newStep(){
        driver = AndroidDriver(URL(serverUrl), caps)
    }

    fun appium_driver(): AppiumDriver<MobileElement> {
        driver = AndroidDriver(URL(serverUrl), caps)
        return driver
    }

    companion object {

//        const val activityName = "org.wikipedia.main.MainActivity"
//        const val appPackage = "com.seamm_team.seamm.debug"
        const val automationName = "UiAutomator2"
        const val platformName = "Android"
        private const val serverUrl = "http://127.0.0.1:4723/wd/hub"

        lateinit var driver: AppiumDriver<MobileElement>
        private val caps = DesiredCapabilities()

        @JvmStatic
        @BeforeAll
        fun setUp() {
            caps.setCapability("automationName", automationName);
            caps.setCapability("platformName", platformName);
            caps.setCapability("deviceName", "Pixel 6a");
            caps.setCapability("version", "15");
            caps.setCapability("app", "/Users/ilnur/AndroidStudioProjects/AppiumKotlinJUnitTutorial/src/test/resources/app.apk")
//            caps.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_PATH, "src/test/resources/app.apk")
            caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true)

        }
    }

    @AfterEach
    fun tearDown() {
        driver.quit()
    }
}

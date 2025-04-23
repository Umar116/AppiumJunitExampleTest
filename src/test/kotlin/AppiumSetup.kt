
import Constants.APP_PATH
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URI
import java.net.URL

open class AppiumSetup {
    private val serverUri = URI(SERVER_URL)
    private val serverUrl: URL = serverUri.toURL()

    @BeforeEach
    fun newStep(){
        driver = AndroidDriver(serverUrl, caps)
    }

    fun appium_driver(): AppiumDriver<MobileElement> {
        driver = AndroidDriver(serverUrl, caps)
        return driver
    }

    companion object {

        private const val AUTOMATION_NAME = "UiAutomator2"
        private const val PLATFORM_NAME = "Android"
        private const val SERVER_URL = "http://127.0.0.1:4723/wd/hub"

        lateinit var driver: AppiumDriver<MobileElement>
        private val caps = DesiredCapabilities()

        @JvmStatic
        @BeforeAll
        fun setUp() {
            caps.setCapability("automationName", AUTOMATION_NAME)
            caps.setCapability("platformName", PLATFORM_NAME)
            caps.setCapability("deviceName", "Pixel 6a")
            caps.setCapability("version", "15")
            caps.setCapability("app", APP_PATH)
            caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true)

        }
    }

    @AfterEach
    fun tearDown() {
        driver.quit()
    }
}

package pages

import utils.Actions
import api.TempMailClient
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait
import kotlin.test.assertEquals

class AuthPage : Actions() {

    private val emailAuthButton =
        By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")
    private val account =
        By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]")
    private val signIn = By.xpath("//android.widget.TextView[@text=\"Sign in\"]")
    private val authTitle = By.xpath(
        "//android.widget.TextView[@text=\"Sign in for\n" +
                "better looks\"]"
    )
    private val googleAuth = By.xpath("//android.widget.TextView[@text=\"Continue with Google\"]")
    private val acceptAccount =
        By.xpath("//android.widget.Button[@resource-id=\"com.google.android.gms:id/continue_button\"]")
    val signInToContinueTitle: By = By.xpath("//android.widget.TextView[@text=\"Sign in to continue\"]")
    private val emailField = By.xpath("//android.widget.EditText")
    private val sendCodeButton = By.xpath("//android.widget.TextView[@text=\"Send security code\"]")
    private val emailLoginTypeButton =
        By.xpath("//android.widget.TextView[@text=\"Continue with email\"]")
    private val emailLoginTitle = By.xpath("//android.widget.TextView[@text=\"Enter your email\"]")

    override val wait = WebDriverWait(driver, 3000)

    fun openLoginPage() {
        click(locator = account)
        click(locator = signIn)
        val title = getObjectText(authTitle)
        assertEquals(
            "Sign in for\n" +
                    "better looks", title
        )
    }

    fun loginThrowGoogle() {
        click(googleAuth)
        click(acceptAccount)
    }


}
package pages

import utils.Actions
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait

class WardrobePage : Actions() {
    override val wait = WebDriverWait(driver, 15)

    fun getBrandName(): String {
        val item = By.className("android.widget.TextView")
//        assert(getElement(item).isDisplayed, { "item not found" })
        val name = getElementTextFromList(item, 0)
        return name
    }
}
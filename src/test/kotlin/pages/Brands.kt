package pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait
import utils.Actions

class Brands : Actions(){

    override val wait = WebDriverWait(driver, 30)

    private val brandPageTitle = By.xpath("//android.widget.TextView[@text=\"Brands\"]")


    fun getBrandsPageTitle(): String {
        val title = getObjectText(locator = brandPageTitle)
        return title
    }
}
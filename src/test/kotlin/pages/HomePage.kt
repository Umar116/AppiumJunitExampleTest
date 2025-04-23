package pages

import Actions
import org.openqa.selenium.By

class HomePage : Actions() {
    private val homePageTitle =
        By.xpath("//android.widget.TextView[@text=\"Your phygital experience\"]")

    fun assertMarketplaceTitle(){
        assertScreenTitle(locator = homePageTitle, "Your phygital experience")
    }
}
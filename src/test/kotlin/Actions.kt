import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.slf4j.LoggerFactory
import kotlin.test.assertEquals

open class Actions : AppiumSetup() {

    private val logger = LoggerFactory.getLogger(this::class.java)
    open val wait = WebDriverWait(driver, 30)

    // == ВСПОМОГАТЕЛЬНЫЕ ==
    private fun waitForVisible(locator: By): WebElement {
        logger.debug("Waiting for visibility of: {}", locator)
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
    }

    private fun waitForAll(locator: By): List<WebElement> {
        logger.debug("Waiting for presence of all elements for: {}", locator)
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator))
    }

    fun isElementVisible(locator: By): Boolean {
        return try {
            waitForVisible(locator)
            true
        } catch (e: Exception) {
            logger.warn("Element not visible: $locator - ${e.message}")
            false
        }
    }

    // == ОСНОВНЫЕ ЭКШЕНЫ ==
    fun click(locator: By) {
        logger.info("Clicking element: $locator")
        waitForVisible(locator).click()
    }

    fun safeClick(locator: By, errorMessage: String = "Element not clickable"): Boolean {
        return try {
            click(locator)
            true
        } catch (e: Exception) {
            logger.error("$errorMessage: ${e.message}")
            false
        }
    }

    fun input(locator: By, text: String) {
        logger.info("Typing '$text' into field: $locator")
        val field = waitForVisible(locator)
        field.sendKeys(text)
    }

    fun inputClean(locator: By, text: String) {
        logger.info("Clearing and typing '$text' into field: $locator")
        val field = waitForVisible(locator)
        field.clear()
        field.sendKeys(text)
    }

    fun getObjectText(locator: By): String {
        logger.info("Getting text from element: $locator")
        return waitForVisible(locator).text
    }

    fun getElementTextFromList(locator: By, index: Int): String {
        val elements = waitForAll(locator)
        require(index in elements.indices) { "Index $index out of bounds for list of size ${elements.size}" }
        val text = elements[index].text
        logger.info("Got text from element list [$index]: $text")
        return text
    }

    fun openLink(url: String) {
        logger.info("Opening deep link: $url")
        driver.get(url)
    }

    fun assertScreenTitle(locator: By, expectedTitle: String) {
        val actualTitle = getObjectText(locator)
        logger.info("Asserting title: expected '$expectedTitle', actual '$actualTitle'")
        assertEquals(expectedTitle, actualTitle, "Screen title does not match.")
    }
}

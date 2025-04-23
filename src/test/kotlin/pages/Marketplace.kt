package pages

import org.openqa.selenium.By
import org.slf4j.LoggerFactory
import utils.Actions

class Marketplace : Actions() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    private val marketplaceTitle = By.xpath("//android.widget.TextView[@text=\"Marketplace\"]")
    private val marketplacePageButton = By.xpath(
        "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]"
    )
    private val itemButton = By.xpath(
        "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]"
    )
    private val buyButton = By.className("android.widget.TextView")
    private val proceedToShippingButton = By.xpath("//android.widget.TextView[@text=\"Proceed to shipping\"]")
    private val addAddressButton = By.xpath("//android.widget.TextView[@text=\"Add address\"]")
    private val shippingAddressPageTitle = By.xpath("//android.widget.TextView[@text=\"Shipping Address\"]")
    private val findAddressField = By.xpath("//android.widget.EditText")
    private val openAddressSearchButton = By.xpath("//android.widget.ScrollView/android.view.View[1]/android.widget.EditText[2]/android.view.View")
    private val addressFromList = By.xpath("//android.widget.TextView[@text=\"California 1\"]")
    private val fullNameField = By.xpath("//android.widget.ScrollView/android.view.View[1]/android.widget.EditText[1]")
    private val zipCodeField = By.xpath("//android.widget.ScrollView/android.view.View[1]/android.widget.EditText[5]")
    private val phoneNumberField = By.xpath("//android.widget.ScrollView/android.view.View[1]/android.widget.EditText[6]")
    private val saveAddressButton = By.xpath("//android.widget.TextView[@text=\"Save address\"]")
    private val processToPaymentButton = By.xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.Button")
    private val checkoutPageTitle = By.xpath("//android.widget.TextView[@text=\"Checkout\"]")
    private val paymentFormTitle = By.xpath("//android.widget.TextView[@text=\"Add your payment information\"]")
    private val cardNumberField = By.xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.EditText[1]")
    private val cardDateField = By.xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.EditText[2]")
    private val cvcCodeField = By.xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.EditText[3]")
    private val zipCodePaymentField = By.xpath("//android.widget.ScrollView/android.view.View[3]/android.widget.EditText")
    private val payByCardButton = By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.seamm_team.seamm.debug:id/label\"]/android.view.View")
    private val sizeListTitle = By.xpath("//android.widget.TextView[@text=\"Select size\"]")
    private val sizeButton = By.xpath("//android.widget.TextView[@text=\"Size\"]")
    private val productTypeButton = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[7]")
    private val productOptionTitle = By.xpath("//android.widget.TextView[@text=\"Choose your option\"]")
    val successPurchasePageTitle: By = By.xpath("//android.widget.TextView[@text=\"Woohoo! Thank you for your purchase.\"]")
    private val claimProductButton: By = By.xpath("//android.widget.TextView[@text=\"Claim\"]")
    val cardDeclineNotification: By = By.xpath("//android.widget.TextView[@text=\"Your card was declined.\"]")

    fun openMarketplace() {
        click(marketplacePageButton)
        assertScreenTitle(marketplaceTitle, "Marketplace")
    }

    fun openItemCard() {
        click(itemButton)
        logger.info("Opened item card")
    }

    fun clickBuyButton() {
        val price = getElementTextFromList(buyButton, 5)
        click(By.xpath("//android.widget.TextView[@text=\"Buy now $price\"]"))
        logger.info("Clicked Buy Now with price: $price")
    }

    fun chooseProductSize(size: String) {
        click(sizeButton)
        assertScreenTitle(sizeListTitle, "Select size")
        click(By.xpath("//android.widget.TextView[@text=\"$size\"]"))
        logger.info("Selected product size: $size")
    }

    fun openProductTypes() {
        click(productTypeButton)
        assertScreenTitle(productOptionTitle, "Choose your option")
    }

    fun chooseProductOption(productType: String) {
        click(By.xpath("//android.widget.TextView[@text=\"$productType\"]"))
        logger.info("Selected product type: $productType")
    }

    private fun openCheckoutPage() {
        click(proceedToShippingButton)
        assertScreenTitle(checkoutPageTitle, "Checkout")
    }

    private fun openInputAddressPage() {
        click(addAddressButton)
        assertScreenTitle(shippingAddressPageTitle, "Shipping Address")
    }

    fun inputShippingAddress() {
        openCheckoutPage()
        openInputAddressPage()
        chooseAddressFromCityList()

        assertScreenTitle(shippingAddressPageTitle, "Shipping Address")

        inputClean(fullNameField, "Umar Testov")
        input(zipCodeField, "34563")
        input(phoneNumberField, "5555555555")

        click(saveAddressButton)
        click(processToPaymentButton)

        logger.info("Shipping address entered and saved")
    }

    private fun chooseAddressFromCityList() {
        click(openAddressSearchButton)
        input(findAddressField, "California")
        click(addressFromList)
        logger.info("Address selected from list")
    }

    fun inputCardData(cardNumber: String, cardDate: String) {
        assertScreenTitle(paymentFormTitle, "Add your payment information")

        input(cardNumberField, cardNumber)
        input(cardDateField, cardDate)
        input(cvcCodeField, "432")
        input(zipCodePaymentField, "34562")

        click(payByCardButton)
        logger.info("Payment info submitted")
    }

    fun openDeepLink(link: String) {
        openLink(link)
    }

    fun claimProduct() {
        click(claimProductButton)
        logger.info("Product claimed")
    }

    fun openBrandsList(){
        click(locator = By.xpath("//android.widget.Button[1]"))
    }
}

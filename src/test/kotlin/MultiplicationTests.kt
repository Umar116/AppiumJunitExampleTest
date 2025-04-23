import api.AdminApi
import org.junit.jupiter.api.Test
import pages.AuthPage
import pages.Brands
import pages.HomePage
import pages.Marketplace
import pages.WardrobePage
import kotlin.test.assertEquals

class MultiplicationTests : AppiumSetup() {

    @Test
    fun buyPhygitalProduct() {
        val loginInApp = AuthPage()
        val marketplace = Marketplace()

        val homePage = HomePage()
        homePage.assertMarketplaceTitle()

        loginInApp.openLoginPage()
        loginInApp.loginThrowGoogle()
        marketplace.openMarketplace()
        marketplace.openItemCard()
        marketplace.clickBuyButton()
        marketplace.chooseProductSize("UK6")
        marketplace.inputShippingAddress()
        marketplace.inputCardData(cardNumber = "4242424242424242", cardDate = "0145")
        assertEquals(
            Constants.SUCCESS_ORDER_NOTIFICATION,
            marketplace.getObjectText(marketplace.successPurchasePageTitle)
        )
    }

    @Test
    fun buyDigitalProduct() {
        val loginInApp = AuthPage()
        val marketplace = Marketplace()

        val homePage = HomePage()
        homePage.assertMarketplaceTitle()

        loginInApp.openLoginPage()
        loginInApp.loginThrowGoogle()
        marketplace.openMarketplace()
        marketplace.openItemCard()
        marketplace.openProductTypes()
        marketplace.chooseProductOption(Constants.DIGITAL_ONLY_PRODUCT)
        marketplace.inputCardData(cardNumber = "4242424242424242", cardDate = "0145")
        assertEquals(
            Constants.SUCCESS_ORDER_NOTIFICATION,
            marketplace.getObjectText(marketplace.successPurchasePageTitle)
        )
    }

    @Test
    fun paymentDeclined() {
        val loginInApp = AuthPage()
        val marketplace = Marketplace()

        val homePage = HomePage()
        homePage.assertMarketplaceTitle()

        loginInApp.openLoginPage()
        loginInApp.loginThrowGoogle()
        marketplace.openMarketplace()
        marketplace.openItemCard()
        marketplace.openProductTypes()
        marketplace.chooseProductOption(Constants.DIGITAL_ONLY_PRODUCT)
        marketplace.inputCardData(cardNumber = "4000000000000002", cardDate = "0145")
        assertEquals(
            Constants.DECLINE_PAYMENT_NOTIFICATION,
            marketplace.getObjectText(marketplace.cardDeclineNotification)
        )
    }

    @Test
    fun redirectingOnAuthPageFromMarketplace() {
        val loginInApp = AuthPage()
        val marketplace = Marketplace()
        val homePage = HomePage()
        homePage.assertMarketplaceTitle()

        marketplace.openMarketplace()
        marketplace.openItemCard()
        marketplace.openProductTypes()
        marketplace.chooseProductOption(Constants.DIGITAL_ONLY_PRODUCT)
        assertEquals(
            "Sign in to continue",
            marketplace.getObjectText(loginInApp.signInToContinueTitle)
        )
    }

    @Test
    fun redirectingOnAuthPageFromWardrobe() {
        val loginInApp = AuthPage()
        val marketplace = Marketplace()
        val homePage = HomePage()

        homePage.assertMarketplaceTitle()
        marketplace.openMarketplace()
        marketplace.openItemCard()
        marketplace.clickBuyButton()
        assertEquals(
            "Sign in to continue",
            marketplace.getObjectText(loginInApp.signInToContinueTitle)
        )
    }

    @Test
    fun itemAddedByDeepLink(){
        val loginInApp = AuthPage()
        val marketplace = Marketplace()
        val wardrobe = WardrobePage()
        val adminApi = AdminApi()

        val link = adminApi.createDigitalItem()
        marketplace.openDeepLink(link.toString())
        loginInApp.loginThrowGoogle()

        marketplace.claimProduct()
        val brand = wardrobe.getBrandName()
        assertEquals("1 People", brand)
    }

    @Test
    fun brandsDisplayed(){
        val homePage = HomePage()
        val brand = Brands()
        val marketplace = Marketplace()

        homePage.assertMarketplaceTitle()
        marketplace.openMarketplace()
        marketplace.openBrandsList()
        val title = brand.getBrandsPageTitle()
        assertEquals(title, "Brands")
    }

}

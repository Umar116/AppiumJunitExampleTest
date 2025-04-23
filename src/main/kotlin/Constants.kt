import java.nio.file.Paths

object Constants {
    const val DIGITAL_ONLY_PRODUCT = "Digital-only"
    const val SUCCESS_ORDER_NOTIFICATION = "Woohoo! Thank you for your purchase."
    const val DECLINE_PAYMENT_NOTIFICATION = "Your card was declined."
    private const val APK_FOLDER = "src/test/resources/"
    private const val APK_FILE = "app.apk"

    val APP_PATH = Paths.get(APK_FOLDER, APK_FILE).toAbsolutePath().toString()
}
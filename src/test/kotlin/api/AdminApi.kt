package api

import io.restassured.RestAssured.given
import org.junit.jupiter.api.Assertions
import io.github.cdimascio.dotenv.dotenv

val dotenv = dotenv()
val email: String = dotenv["ADMIN_LOGIN"]
val password: String = dotenv["ADMIN_PASSWORD"]
val apiUrl: String = dotenv["ADMIN_URL"]

class AdminApi {
    private fun getAdminToken(): String? {
        val url = "$apiUrl/auth/verify-otp"
        val body = "{\n" +
                "  \"code\": \"$password\",\n" +
                "  \"email\": \"$email\"\n}"
        val response = given()
            .header("Content-type", "application/json")
            .and().body(body).`when`().post(url).then().extract().response()

        Assertions.assertEquals(200, response.statusCode())

        return response.jsonPath().getString("accessToken")
    }


    private fun getDigitalModel(): String {
        val token = getAdminToken()
        val url = "$apiUrl/digitalmdl"

        val response = given()
            .param("isActive", true)
            .headers(
                "Content-type", "application/json",
                "Authorization", "Bearer $token"
            )
            .and().`when`().get(url).then().extract().response()
        return response.jsonPath().getString("records[0].id")
    }

    fun createDigitalItem(): String? {
        val token = getAdminToken()
        val url = "$apiUrl/digitalitem"
        val digitalModelId = getDigitalModel()
        val body = "{\n" +
                "  \"digitalModelId\": \"$digitalModelId\", \n" +
                "  \"isActive\": \"true\",\n" +
                "  \"estimatedPriceCents\": \"0\" \n}"
        val response = given()
            .headers(
                "Content-type", "application/json",
                "Authorization", "Bearer $token"
            )
            .and().body(body).`when`().post(url).then().extract().response()
        return response.jsonPath().getString("deeplink")
    }
}
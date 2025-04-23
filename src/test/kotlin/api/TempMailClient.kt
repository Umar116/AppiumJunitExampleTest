package api

import io.restassured.RestAssured.given
import io.restassured.response.Response
import kotlin.test.assertEquals

class TempMailClient {
    private val url = "https://tempmail.plus/api/mails"
    private fun `get first id`(email: String): Response? {
        val response = given()
            .param("email", email)
            .headers(
                "Content-type", "application/json",
            )
            .and().`when`().get(url).then().extract().response()
        assertEquals(response.statusCode, 200)
        return response
    }

    fun `get code`(email: String): String {
        val converter = Helpers()
        val firstId = `get first id`(email)
        Thread.sleep(15000)
        val response = given()
            .param("email", email)
            .headers(
                "Content-type", "application/json",
            )
            .and().`when`().get("$url/$firstId").then().extract().response()
        assertEquals(response.statusCode, 200)
        val id = response.jsonPath().getString("text")
        val code = converter.extractDigitalCodeFromMail(id)
        return code
    }
}
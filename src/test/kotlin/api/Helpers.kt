package api
import java.util.regex.Pattern

class Helpers {

    fun extractDigitalCodeFromMail(input: String): String {
        val regex = """\n\*{4}\n(\d{4})\n\*{4}\n"""
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(input)

        return if (matcher.find()) {
            matcher.group(1) ?: throw IllegalArgumentException("Код не найден в тексте ответа")
        } else {
            throw IllegalArgumentException("Код не найден в тексте ответа")
        }
    }
}
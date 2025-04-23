package dates
import net.datafaker.Faker


class FakeDates {

    fun `fake mail`(): String {
        val faker = Faker()
        val email = faker.name().firstName() + "@mailto.plus"
        return email
    }


}
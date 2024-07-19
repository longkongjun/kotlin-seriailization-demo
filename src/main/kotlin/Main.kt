import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import polymorphic.*

fun main() {
    val json = Json {
        serializersModule = SerializersModule {
            polymorphic(City::class) {
                subclass(Beijing::class, Beijing.serializer())
                subclass(Shanghai::class, Shanghai.serializer())
                subclass(NingBo::class, NingBo.serializer())
                subclass(Hangzhou::class, Hangzhou.serializer())
            }
        }
    }

    println(json.encodeToString(projects()))
}

private fun animals(): List<Animal> {
    return listOf(
        Dog("dog"),
        Cat("cat"),
        GoldFish("fish"),
        Shark("shark"),
        Bird("Bird"),
    )
}

private fun languages(): List<Language> {
    return listOf(
        Kotlin("1.5.21"),
        Java("16"),
        Python("3.9.5")
    )
}

private fun cities(): List<City> {
    return listOf(
        Beijing("Beijing"),
        Shanghai,
        Hangzhou("hangzhou"),
        NingBo,
    )
}

private fun projects(): List<Project> {
    return listOf(
        Project("base"),
        PublicProject("public", "key2"),
        PrivateProject("private", "password"),
    )
}
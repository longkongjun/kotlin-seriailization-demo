package polymorphic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Animal {
    abstract val name: String
}

@Serializable
@SerialName("dogdogdog")
data class Dog(
    override val name: String
) : Animal()

@Serializable
data class Cat(
    override val name: String
) : Animal()

@Serializable
sealed class Fish : Animal()

@Serializable
data class GoldFish(override val name: String) : Fish()

@Serializable
data class Shark(override val name: String) : Fish()

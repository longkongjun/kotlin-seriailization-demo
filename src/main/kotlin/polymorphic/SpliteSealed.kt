package polymorphic

import kotlinx.serialization.Serializable

@Serializable
data class Python(
    val version: String
) : Language

@Serializable
data class Bird(
    override val name: String
) : Animal()
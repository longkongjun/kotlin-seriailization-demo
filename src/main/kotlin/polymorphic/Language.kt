package polymorphic

import kotlinx.serialization.Serializable

@Serializable
sealed interface Language

@Serializable
data class Kotlin(
    val version: String
) : Language

@Serializable
data class Java(
    val version: String
) : Language
package polymorphic

import kotlinx.serialization.Serializable

interface City

@Serializable
data class Beijing(
    val name: String
) : City

@Serializable
object Shanghai : City

@Serializable
sealed interface ZheJiang : City

@Serializable
data object NingBo : ZheJiang

@Serializable
data class Hangzhou(
    val name: String
) : ZheJiang
@file:UseSerializers(SubjectAsIntSerializer::class)
package model

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import serializer.SubjectAsIntSerializer

@Serializable
enum class Subject(val type: Int, val nameCn: String) {
    CHINESE(1, "语文"),
    MATH(2, "数学"),
    ENGLISH(3, "英语")
}

@Serializable
data class SubjectInfo(
    val subjectType: Int,
    val desc: String
)

@Serializable
data class Project(
    val name: String
)

@Serializable
data class ProjectInfo<T>(
    val name: String,
    val desc: T,
)
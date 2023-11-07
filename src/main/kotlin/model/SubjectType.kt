package model

import kotlinx.serialization.Serializable

@Serializable
enum class SubjectType(val type: Int, val nameCn: String) {
    CHINESE(1, "语文"),
    MATH(2, "数学"),
    ENGLISH(3, "英语")
}
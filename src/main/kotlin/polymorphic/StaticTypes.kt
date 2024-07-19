package polymorphic

import kotlinx.serialization.Serializable

@Serializable
open class Project(open val name: String)

@Serializable
data class PrivateProject(val privateName: String, val password: String) : Project(privateName)

@Serializable
data class PublicProject(val publicName: String, val key: String) : Project(publicName)
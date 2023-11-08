package serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import model.Subject
import model.SubjectInfo

object SubjectAsIntSerializer : KSerializer<Subject> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("model.Subject", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): Subject {
        val subjectType = decoder.decodeInt()
        return Subject.entries.find { it.type == subjectType }
            ?: throw SerializationException("Unknown subject type: $subjectType")
    }

    override fun serialize(encoder: Encoder, value: Subject) {
        encoder.encodeInt(value.type)
    }
}

object SubjectAsStringSerializer : KSerializer<Subject> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("model.Subject", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Subject {
        val subjectName = decoder.decodeString()
        return Subject.entries.find { it.nameCn == subjectName }
            ?: throw SerializationException("Unknown subject: $subjectName")
    }

    override fun serialize(encoder: Encoder, value: Subject) {
        encoder.encodeString(value.nameCn)
    }
}

@OptIn(ExperimentalSerializationApi::class)
object SubjectDelegateSerializer : KSerializer<Subject> {

    private val delegate = SubjectInfo.serializer()

    override val descriptor: SerialDescriptor = SerialDescriptor("model.Subject", delegate.descriptor)

    override fun deserialize(decoder: Decoder): Subject {
        val subjectInfo = decoder.decodeSerializableValue(delegate)
        return Subject.entries.find { it.type == subjectInfo.subjectType }
            ?: throw SerializationException("Unknown subject type: ${subjectInfo.subjectType}")
    }

    override fun serialize(encoder: Encoder, value: Subject) {
        encoder.encodeSerializableValue(delegate, SubjectInfo(value.type, value.nameCn))
    }
}

object SubjectProxySerializer : KSerializer<Subject> {

    private val proxy = SubjectInfo.serializer()

    override val descriptor: SerialDescriptor = proxy.descriptor

    override fun deserialize(decoder: Decoder): Subject {
        val subjectInfo = decoder.decodeSerializableValue(proxy)
        return Subject.entries.find { it.type == subjectInfo.subjectType }
            ?: throw SerializationException("Unknown subject type: ${subjectInfo.subjectType}")
    }

    override fun serialize(encoder: Encoder, value: Subject) {
        encoder.encodeSerializableValue(proxy, SubjectInfo(value.type, value.nameCn))
    }
}
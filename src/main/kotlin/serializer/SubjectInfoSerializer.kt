package serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import model.SubjectInfo

object SubjectInfoSerializer : KSerializer<SubjectInfo> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("model.SubjectInfo") {
        element<Int>("subjectType")
        element<String>("desc")
    }

    override fun deserialize(decoder: Decoder): SubjectInfo {
        return decoder.decodeStructure(descriptor) {
            var subjectType: Int? = null
            var desc: String? = null
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> subjectType = decodeIntElement(descriptor, 0)
                    1 -> desc = decodeStringElement(descriptor, 1)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> throw SerializationException("Unexpected index: $index")
                }
            }
            requireNotNull(subjectType) { "subjectType is null" }
            requireNotNull(desc) { "desc is null" }
            SubjectInfo(subjectType, desc)
        }
    }

    override fun serialize(encoder: Encoder, value: SubjectInfo) {
        encoder.encodeStructure(descriptor) {
            encodeIntElement(descriptor, 0, value.subjectType)
            encodeStringElement(descriptor, 1, value.desc)
        }
    }
}
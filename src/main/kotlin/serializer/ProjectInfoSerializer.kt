package serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import model.ProjectInfo

class ProjectInfoSerializer<T>(private val typeSerializer: KSerializer<T>) : KSerializer<ProjectInfo<T>> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("model.ProjectInfo") {
        element<String>("name")
        element("desc", typeSerializer.descriptor)
    }

    override fun deserialize(decoder: Decoder): ProjectInfo<T> {
        return decoder.decodeStructure(descriptor) {
            var name: String? = null
            var desc: T? = null
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> name = decodeStringElement(descriptor, 0)
                    1 -> desc = decodeSerializableElement(descriptor, 1, typeSerializer)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> throw SerializationException("Unexpected index: $index")
                }
            }
            requireNotNull(name) { "name is null" }
            requireNotNull(desc) { "desc is null" }
            ProjectInfo(name, desc)
        }
    }

    override fun serialize(encoder: Encoder, value: ProjectInfo<T>) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.name)
            encodeSerializableElement(descriptor, 1, typeSerializer, value.desc)
        }
    }
}
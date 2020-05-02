package edu.ucmo.nacha.service.serialization

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.google.inject.Inject

class SerializerRegistrar @Inject constructor(val recordFieldSerializer: RecordFieldSerializer) {

    fun register(mapper: ObjectMapper) {
        val module = SimpleModule()
        module.addSerializer(recordFieldSerializer)
        mapper.registerModule(module)
    }
}

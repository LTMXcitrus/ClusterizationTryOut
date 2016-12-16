package generic.modelgen

import generic.model.Field


object DataFormat{
     val fields: MutableList<Field> = getVirginModel()

    fun getVirginModel(): MutableList<Field>{
        return mutableListOf(
                Field("dim1", "Double", 0.0, ""),
                Field("dim2", "Double", 0.0, ""),
                Field("dim3", "Double", 0.0, ""),
                Field("dim4", "Double", 0.0, ""),
                Field("dim5", "Double", 0.0, "")
        )
    }
}
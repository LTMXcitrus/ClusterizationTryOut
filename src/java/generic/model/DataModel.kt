package generic.model

object DataModel{

    val fields: MutableList<Field> = getVirginFields()

    fun getVirginFields(): MutableList<Field>{
        return mutableListOf(
                Field("age", "Double", 0.0, ""),
                Field("workclass", "String", 0.0, ""),
                Field("fnlwgt", "Double", 0.0, ""),
                Field("education", "String", 0.0, ""),
                Field("education_num", "Double", 0.0, ""),
                Field("marital_status", "String", 0.0, ""),
                Field("occupation", "String", 0.0, ""),
                Field("relationship", "String", 0.0, ""),
                Field("race", "String", 0.0, ""),
                Field("gender", "String", 0.0, ""),
                Field("capital_gain", "Double", 0.0, ""),
                Field("capital_loss", "Double", 0.0, ""),
                Field("hours_per_week", "Double", 0.0, ""),
                Field("native_country", "String", 0.0, ""),
                Field("income_bracket", "String", 0.0, "")
        )
    }
}



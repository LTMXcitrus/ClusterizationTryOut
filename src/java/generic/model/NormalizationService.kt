package generic.model


object NormalizationService {
    fun getNormalizationObject(objects: List<DataLine>): DataLine {
        val size = objects.size.toDouble()
        var fields: MutableList<Field> = DataModel.getVirginFields()
        objects.forEach { line ->
            line.fields.forEachIndexed { i, field ->
                fields[i].value = fields[i].value + line.fields[i].value
            }
        }
        fields = fields.map { field ->
            Field(field.name, field.type, field.value / size)
        }.toMutableList()
        return DataLine(fields)
    }
}
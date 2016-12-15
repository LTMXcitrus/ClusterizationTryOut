package generic.model


object NormalizationService {
    fun getNormalizationObject(objects: List<DataLine>): DataLine {
        val size = objects.size.toDouble()
        var fields: MutableList<Field> = StringToValuesService.getEmptyObject().fields
        objects.forEach { line ->
            line.fields.forEachIndexed { i, field ->
                fields[i].value = fields[i].value + field.value
            }
        }
        fields = fields.map { field ->
            Field(field.name, field.type, field.value / size, field.rawValue)
        }.toMutableList()
        return DataLine(fields)
    }
}
package generic.model


object NormalizationService {
    fun getMeanObject(objects: List<DataLine>): DataLine {
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

    fun getStanDevObject(objects: List<DataLine>, meanVector: DataLine): DataLine {
        val size = objects.size.toDouble()
        val fields: MutableList<Field> = StringToValuesService.getEmptyObject().fields
        objects.forEach { line ->
            line.fields.forEachIndexed { i, field ->
                fields[i].value = fields[i].value + Math.pow(field.value, 2.0) / size
            }
        }
        fields.forEachIndexed { i, field ->
            field.value = Math.sqrt(field.value - meanVector.fields[i].value)
        }
        return DataLine(fields)
    }
}
package generic.model

import generic.model.Field
import javax.xml.crypto.Data


data class DataLine(
        val fields: MutableList<Field>
) {

    constructor(dataline: DataLine): this(dataline.fields){

    }

    override fun toString(): String {
        return fields.joinToString(", ")
    }

    fun unNormalize(normalizationVector: DataLine): DataLine {
        val newFields: MutableList<Field> = mutableListOf()
        fields.forEachIndexed { i, field ->
            newFields.add(
                    Field(field.name,
                            field.type,
                            field.value * normalizationVector.fields[i].value))
        }
        return DataLine(newFields)
    }

    fun normalize(normalizationVector: DataLine): DataLine {
        val newFields: MutableList<Field> = StringToValuesService.getEmptyObject().fields
        fields.forEachIndexed { i, field ->
            newFields[i].value = field.value / normalizationVector.fields[i].value
        }
        return DataLine(newFields)
    }

    fun dist(dataLine: DataLine): Double {
        var sum = 0.0
        fields.forEachIndexed { i, field ->
            sum += Math.pow(field.value - dataLine.fields[i].value, 2.0)
        }
        return Math.sqrt(sum)
    }

    fun removeField(field: Field){
        fields.remove(field)
    }

    fun addField(field: Field){
        fields.add(field)
    }
}
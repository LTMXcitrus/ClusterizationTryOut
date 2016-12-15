package generic.model

import java.util.HashMap
import java.util.HashSet

object StringToValuesService {

    val stringValues: HashMap<String, HashSet<String>> = hashMapOf()
    fun findStringValues(objects: List<DataLine>) {
        objects.forEach { line ->
            line.fields.forEach { field ->
                if (field.type == "String") {
                    val hashSet = stringValues.getOrPut(field.name) { hashSetOf() }
                    hashSet.add(field.rawValue)
                }
            }
        }
    }

    fun unraw(line: DataLine): DataLine {
        val newFields = mutableListOf<Field>()
        line.fields.forEach { newFields.add(it) }
        newFields.forEach { field ->
            if (stringValues.containsKey(field.name)) {
                line.removeField(field)
                stringValues[field.name]!!.forEach { value ->
                    line.addField(Field("${field.name}_$value",
                            field.type,
                            if (field.rawValue == value) 1.0 else 0.0, field.rawValue))
                }
            }
        }
        line.fields.forEach { field ->
            if(field.isNumber() && field.rawValue != ""){
                field.value = field.rawValue.toDouble()
            }
        }
        return line
    }

    fun getEmptyObject(): DataLine {
        return unraw(DataLine(DataModel.getVirginFields()))
    }

    fun getValue(field: Field): Double {
        return if (field.type == "String") {
            stringValues[field.name]?.indexOf(field.rawValue)!! + 1.0
        } else {
            field.rawValue.toDouble()
        }
    }

    fun numerize(lines: List<DataLine>): List<DataLine> {
        findStringValues(lines)
        return lines.map { unraw(it) }
    }
}
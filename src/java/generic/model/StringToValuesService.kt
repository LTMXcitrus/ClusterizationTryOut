package generic.model

import java.util.HashMap
import java.util.HashSet

object StringToValuesService {

    val stringValues: HashMap<String, HashSet<String>> = hashMapOf()
    fun findStringValues(objects: List<DataLine>) {
        objects.forEach { line ->
            line.fields.forEachIndexed { i, field ->
                if (field.type == "String") {
                    val hashSet = stringValues.getOrPut(field.name) { hashSetOf() }
                    hashSet.add(field.rawValue)
                }
            }
        }
    }

    fun unraw(line: DataLine): DataLine {
        val fields: MutableList<Field> = DataModel.getVirginFields()
        line.fields.forEachIndexed { i, field ->
            fields[i].value = getValue(field)
        }
        return DataLine(fields)
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
package ungeneric.model

import java.util.HashMap
import java.util.HashSet


object StringToValuesService {


    val stringValues: HashMap<String, HashSet<String>> = hashMapOf()

    fun findStringValues(objects: List<DataLineRaw>) {
        objects.forEach { line ->
            for (field in line.javaClass.declaredFields) {
                field.isAccessible = true
                if (field.type.simpleName == "String") {
                    val value = field.get(line) as String
                    val hashMap = stringValues.getOrPut(field.name) { hashSetOf() }
                    hashMap.add(value)
                }
            }
        }
        println(stringValues)
    }

    fun unraw(raw: DataLineRaw): DataLine {
        with(raw) {
            return DataLine(
                    age,
                    getValue("workclass", workclass),
                    fnlwgt,
                    getValue("education", education),
                    education_num,
                    getValue("marital_status", marital_status),
                    getValue("occupation", occupation),
                    getValue("relationship", relationship),
                    getValue("race", race),
                    getValue("gender", gender),
                    capital_gain,
                    capital_loss,
                    hours_per_week,
                    getValue("native_country", native_country),
                    getValue("income_bracket", income_bracket)
            )
        }
    }

    fun getValue(fieldName:String, stringValue: String): Int{
        return stringValues[fieldName]?.indexOf(stringValue)!! + 1
    }

    fun numerize(lines: List<DataLineRaw>): List<DataLine> {
        findStringValues(lines)
        val result: MutableList<DataLine> = mutableListOf()
        lines.forEach { result.add(unraw(it))  }
        return result
    }
}



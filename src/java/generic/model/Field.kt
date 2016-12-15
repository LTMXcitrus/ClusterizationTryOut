package generic.model

import java.text.DecimalFormat

data class Field(val name: String, val type: String, var value: Double, var rawValue: String = ""){

    override fun toString(): String {
        return "$name: ${valueAsString()}"
    }

    fun valueAsString(): String{
        return if(isNumber()){
            DecimalFormat("#.##").format(value)
        }
        else{
            "${DecimalFormat("#.##").format(value)}%"
        }
    }

    fun isNumber(): Boolean{
        return type == "Double"
    }
}



package generic.model

data class Field(val name: String, val type: String, var value: Double, var rawValue: String = "") {

    override fun toString(): String {
        return "$name: $value"
    }

    fun isNunmber(): Boolean {
        return type != "String"
    }
}
package generic.modelgen

import generic.model.DataLine
import generic.model.Field
import java.util.Random


object GenModel {

    fun genModel(): MutableList<DataLine> {
        val model: MutableList<DataLine> = mutableListOf()
        val random = Random()
        for (i in 0..3000) {
            val dataline: DataLine = DataLine(mutableListOf())
            val int = random.nextInt(5) + 1
            for (j in 0..4) {
                val double = (random.nextDouble() - 0.5) / 10
                dataline.addField(Field("dim${j + 1}", "Double", int - double))
            }
            model.add(dataline)
        }
        return model
    }

    override fun toString(): String {
        val builder = StringBuilder()
        val model = genModel()
        model.forEach { dataLine ->
            dataLine.fields.forEach { field ->
                builder.append("${field.value}, ")
            }
            builder.delete(builder.length-2, builder.length)
            builder.appendln()
        }
        return builder.toString()
    }
}



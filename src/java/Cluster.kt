import model.DataLine

class Cluster(val id: Int, val objects: MutableList<DataLine> = mutableListOf(), var centroid: DataLine = DataLine(0.0, 0.0, .0, 0.0, 0.0, 0.0)) {

    fun updateCentroid() {
        val size = objects.size.toDouble()
        var age = 0.0
        var fnlwgt = 0.0
        var education_num = 0.0
        var capital_gain = 0.0
        var capital_loss = 0.0
        var hours_per_week = 0.0
        objects.forEach { line ->
            age += line.age
            fnlwgt += line.fnlwgt
            education_num += line.education_num
            capital_gain += line.capital_gain
            capital_loss += line.capital_loss
            hours_per_week += line.hours_per_week
        }
        centroid = DataLine(age/size, fnlwgt/size, education_num/size, capital_gain/size, capital_loss/size, hours_per_week/size)
    }

}


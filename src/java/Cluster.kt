import model.DataLine

class Cluster(val id: Int, val objects: MutableList<DataLine> = mutableListOf(), var centroid: DataLine = DataLine(0.0, 0.0, .0, 0.0, 0.0, 0.0)) {

    fun centroid() {
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
        centroid = DataLine(age, fnlwgt, education_num, capital_gain, capital_loss, hours_per_week)
    }

}


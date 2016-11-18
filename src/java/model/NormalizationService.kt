package model


object NormalizationService {

    fun getNormalizationObject(objects: List<DataLine>): DataLine {
        val size = objects.size
        var age = 0.0
        var fnlwgt = 0.0
        var education_num = 0.0
        var capital_gain = 0.0
        var capital_loss = 0.0
        var hours_per_week = 0.0
        objects.forEach { datum ->
            age += datum.age
            fnlwgt += datum.fnlwgt
            education_num += datum.education_num
            capital_gain += datum.capital_gain
            capital_loss += datum.capital_loss
            hours_per_week += datum.hours_per_week
        }
        age /= size
        fnlwgt /= size
        education_num /= size
        capital_gain /= size
        capital_loss /= size
        hours_per_week /= size
        return DataLine(age, fnlwgt, education_num, capital_gain, capital_loss, hours_per_week)
    }
}



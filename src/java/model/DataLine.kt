package model

data class DataLine(val age: Double,
        //val workclass:String,
                    val fnlwgt: Double,
        //val education:String,
                    val education_num: Double,
        //val marital_status:String,
        //val occupation: String,
        //val replationship: String,
        //val race: String,
        //val gender: String,
                    val capital_gain: Double,
                    val capital_loss: Double,
                    val hours_per_week: Double
        //val native_country: String,
        //val income_bracket: String)
) {
    override fun toString(): String {
        return "age: ${age}, fnlwgt: $fnlwgt, education_num: $education_num," +
                " capital_gain: $capital_gain, capital_loss: $capital_loss, hours_per_week: $hours_per_week"
    }

    fun unNormalize(normalizationVector: DataLine):DataLine{
        return DataLine(age*normalizationVector.age,
                fnlwgt*normalizationVector.fnlwgt,
                education_num*normalizationVector.education_num,
                capital_gain*normalizationVector.capital_gain,
                capital_loss*normalizationVector.capital_loss,
                hours_per_week*normalizationVector.hours_per_week
        )
    }

    fun normalize(normalizationVector: DataLine):DataLine{
        return DataLine(age/normalizationVector.age,
                        fnlwgt/normalizationVector.fnlwgt,
                        education_num/normalizationVector.education_num,
                        capital_gain/normalizationVector.capital_gain,
                        capital_loss/normalizationVector.capital_loss,
                        hours_per_week/normalizationVector.hours_per_week
                )
    }

    fun sum(dataLine: DataLine): DataLine {
        return DataLine(
                dataLine.age + age,
                dataLine.fnlwgt + fnlwgt,
                dataLine.education_num + education_num,
                dataLine.capital_gain + capital_gain,
                dataLine.capital_loss + capital_loss,
                dataLine.hours_per_week + hours_per_week)
    }

    fun dist(dataLine: DataLine): Double {
        var sum = 0.0
        sum += (age - dataLine.age)*(age - dataLine.age)
        sum += (fnlwgt - dataLine.fnlwgt)*(fnlwgt - dataLine.fnlwgt)
        sum += (education_num - dataLine.education_num)*(education_num - dataLine.education_num)
        sum += (capital_gain - dataLine.capital_gain)*(capital_gain - dataLine.capital_gain)
        sum += (capital_loss - dataLine.capital_loss)*(capital_loss - dataLine.capital_loss)
        sum += (hours_per_week - dataLine.hours_per_week)*(hours_per_week - dataLine.hours_per_week)
        return Math.sqrt(sum)
    }
}


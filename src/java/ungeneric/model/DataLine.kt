package ungeneric.model

data class DataLine(val age: Double,
                    val workclass: Int,
                    val fnlwgt: Double,
                    val education: Int,
                    val education_num: Double,
                    val marital_status: Int,
                    val occupation: Int,
                    val relationship: Int,
                    val race: Int,
                    val gender: Int,
                    val capital_gain: Double,
                    val capital_loss: Double,
                    val hours_per_week: Double,
                    val native_country: Int,
                    val income_bracket: Int
) {
    override fun toString(): String {
        return "age: $age, workclass: $workclass, fnlwgt: $fnlwgt, education: $education," +
                " education_num: $education_num, marital_status: $marital_status, occupation: $occupation," +
                " relationship: $relationship, race: $race, gender: $gender, capital_gain: $capital_gain," +
                " capital_loss: $capital_loss, hours_per_week: $hours_per_week, native_country: $native_country," +
                " income_bracket: $income_bracket"
    }

    fun unNormalize(normalizationVector: DataLine):DataLine{
        return DataLine(age * normalizationVector.age,
                workclass * normalizationVector.workclass,
                fnlwgt * normalizationVector.fnlwgt,
                education * normalizationVector.education,
                education_num * normalizationVector.education_num,
                marital_status * normalizationVector.marital_status,
                occupation * normalizationVector.occupation,
                relationship * normalizationVector.occupation,
                race * normalizationVector.race,
                gender * normalizationVector.gender,
                capital_gain * normalizationVector.capital_gain,
                capital_loss * normalizationVector.capital_loss,
                hours_per_week * normalizationVector.hours_per_week,
                native_country * normalizationVector.native_country,
                income_bracket * normalizationVector.income_bracket
        )
    }

    fun normalize(normalizationVector: DataLine):DataLine{
        return DataLine(age / normalizationVector.age,
                workclass / normalizationVector.workclass,
                fnlwgt / normalizationVector.fnlwgt,
                education / normalizationVector.education,
                education_num / normalizationVector.education_num,
                marital_status / normalizationVector.marital_status,
                occupation / normalizationVector.occupation,
                relationship / normalizationVector.occupation,
                race / normalizationVector.race,
                gender / normalizationVector.gender,
                capital_gain / normalizationVector.capital_gain,
                capital_loss / normalizationVector.capital_loss,
                hours_per_week / normalizationVector.hours_per_week,
                native_country / normalizationVector.native_country,
                income_bracket / normalizationVector.income_bracket
                )
    }

    fun sum(dataLine: DataLine): DataLine {
        return DataLine(
                dataLine.age + age,
                dataLine.workclass + workclass,
                dataLine.fnlwgt + fnlwgt,
                dataLine.education + education,
                dataLine.education_num + education_num,
                dataLine.marital_status + marital_status,
                dataLine.occupation + occupation,
                dataLine.relationship + relationship,
                dataLine.race + race,
                dataLine.gender + gender,
                dataLine.capital_gain + capital_gain,
                dataLine.capital_loss + capital_loss,
                dataLine.hours_per_week + hours_per_week,
                dataLine.native_country + native_country,
                dataLine.income_bracket + income_bracket)
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


package ungeneric.model


object NormalizationService {

    fun getNormalizationObject(objects: List<DataLine>): DataLine {
        val size = objects.size
        var age = 0.0
        var workclass = 0
        var fnlwgt = 0.0
        var education = 0
        var education_num = 0.0
        var marital_status = 0
        var occupation = 0
        var relationship = 0
        var race = 0
        var gender = 0
        var capital_gain = 0.0
        var capital_loss = 0.0
        var hours_per_week = 0.0
        var native_country = 0
        var income_bracket = 0
        objects.forEach { line ->
            age += line.age
            workclass += line.workclass
            fnlwgt += line.fnlwgt
            education += line.education
            education_num += line.education_num
            marital_status += line.marital_status
            occupation += line.occupation
            relationship += line.relationship
            race += line.race
            gender += line.gender
            capital_gain += line.capital_gain
            capital_loss += line.capital_loss
            hours_per_week += line.hours_per_week
            native_country += line.native_country
            income_bracket += line.income_bracket
        }
        return DataLine(
                age/size,
                workclass/size,
                fnlwgt/size,
                education/size,
                education_num/size,
                marital_status/size,
                occupation/size,
                relationship/size,
                race/size,
                gender/size,
                capital_gain/size,
                capital_loss/size,
                hours_per_week/size,
                native_country/size,
                income_bracket/size
        )
    }
}



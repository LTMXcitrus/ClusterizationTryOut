import generic.model.DataLine
import generic.model.DataModel
import generic.model.NormalizationService.getNormalizationObject

class Cluster(val id: Int, val objects: MutableList<DataLine> = mutableListOf(), var centroid: DataLine = DataLine(DataModel.getVirginFields())) {

    fun updateCentroid() {
        centroid = getNormalizationObject(objects)
    }

}


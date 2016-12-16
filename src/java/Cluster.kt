import generic.model.DataLine
import generic.model.DataModel
import generic.model.NormalizationService.getMeanObject

class Cluster(val id: Int, val objects: MutableList<DataLine> = mutableListOf(), var centroid: DataLine = DataLine(DataModel.getVirginFields())) {

    fun updateCentroid(normalizationVector: DataLine? = null) {
        centroid = getMeanObject(objects)
    }

}


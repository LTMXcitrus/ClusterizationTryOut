import generic.model.DataLine
import generic.model.NormalizationService

object Visualization{

    fun recap(normalizationVector: DataLine){
        val endClustersCentroids = mutableListOf<DataLine>()
        ClusterService.clusters.forEach { cluster ->
            endClustersCentroids.add(cluster.centroid.unNormalize(normalizationVector))
        }
        normalizationVector.fields.forEachIndexed { i, field ->
            var fieldRecap = ""
            endClustersCentroids.forEach { centroid ->
                fieldRecap += "${centroid.fields[i].valueAsString()} --- "
            }
            println("${field.name}: ${fieldRecap.substring(0, fieldRecap.length-4)}")
        }
    }
}
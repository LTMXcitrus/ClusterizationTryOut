import generic.model.DataLine
import generic.model.NormalizationService

object Visualization{

    fun recap(normalizationVector: DataLine){
        ClusterService.clusters.forEach { cluster ->
            println(cluster.centroid.unNormalize(normalizationVector))
        }
    }
}
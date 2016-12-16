import generic.model.DataLine
import generic.model.NormalizationService

object Visualization{

    fun recap(normalizationVector: DataLine, objects: List<DataLine>){
        val endClustersCentroids = mutableListOf<DataLine>()
        ClusterService.clusters.forEach { cluster ->
            endClustersCentroids.add(cluster.centroid.unNormalize(objects))
        }
        println("centres des clusters: ")
        println("     cluster 1 --- cluster 2 --- cluster 3 --- cluster 4 --- cluster 5")
        normalizationVector.fields.forEachIndexed { i, field ->
            var fieldRecap = ""
            endClustersCentroids.forEach { centroid ->
                fieldRecap += "${centroid.fields[i].valueAsString()}     ---     "
            }
            println("${field.name}:    ${fieldRecap.substring(0, fieldRecap.length - 4)}")
        }
        println("------------------------------------------------------------------")
        ClusterService.clusters.forEachIndexed { i, cluster ->
            cluster.updateCentroid()
            val centroid = cluster.centroid.unNormalize(objects)
            print("cluster ${i + 1}: ")
            centroid.fields.forEach { print("$it --- ") }
            println()
        }
        println("------------------------------------------------------------------")
        ClusterService.clusters.forEachIndexed { i, cluster ->
            println("cluster ${i + 1}: ${cluster.objects.size} éléments")
        }
    }
}
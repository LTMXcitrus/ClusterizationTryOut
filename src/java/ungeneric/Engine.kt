/*
package ungeneric

import ungeneric.model.DataLine
import ungeneric.model.DataLineRaw
import ungeneric.model.NormalizationService
import ungeneric.model.StringToValuesService.findStringValues
import ungeneric.model.StringToValuesService.numerize
import java.util.Random

object Engine {

    var rawData: List<DataLineRaw> = listOf()

    fun loadData(): List<DataLineRaw> {
        val stream = DataLine::class.java.getResourceAsStream("/data.csv")
        val reader = stream.bufferedReader()
        val linesAsStream = reader.lines()
        val lines = linesAsStream.toArray()
        return lines.map { readDataLine(it) }
    }

    fun readDataLine(line: Any): DataLineRaw {
        val lineAsString = line as String
        val data = lineAsString.split(", ")
        return DataLineRaw(
                data[0].toDouble(),
                data[1],
                data[2].toDouble(),
                data[3],
                data[4].toDouble(),
                data[5],
                data[6],
                data[7],
                data[8],
                data[9],
                data[10].toDouble(),
                data[11].toDouble(),
                data[12].toDouble(),
                data[13],
                data[14])
    }


    fun clusterize(nbOfclusters: Int, threshold: Double) {

        var perf = -1.0
        var progressSpeedInd = -1.0

        ClusterService.createClusters(nbOfclusters)
        val data = numerize(loadData())
        val normalizationVector = NormalizationService.getNormalizationObject(data)
        val normalizedData = data.map { datum ->
            datum.normalize(normalizationVector)
        }

        ClusterService.clusters.forEach { cluster ->
            val random = Random()
            val lineId = random.nextInt(normalizedData.size) + 1
            cluster.objects.add(normalizedData[lineId])
        }
        println(normalizedData[0])
        ClusterService.clusters.forEach { it.updateCentroid() }


        while (progressSpeedInd == -1.0 || progressSpeedInd > threshold) {
            ClusterService.clusters.forEach { it.objects.clear() }
            normalizedData.forEach { line ->
                var closestId = -1
                var minDist = -1.0
                ClusterService.clusters.forEach { cluster ->
                    val dist = cluster.centroid.dist(line)
                    if (minDist == -1.0 || minDist > dist) {
                        minDist = dist
                        closestId = cluster.id
                    }
                }
                ClusterService.clusters[closestId - 1].objects.add(line)
            }

            var distMean = 0.0
            val centroids = mutableListOf<DataLine>()
            ClusterService.clusters.forEach { centroids.add(it.centroid) }
            ClusterService.clusters.forEach { it.updateCentroid() }
            ClusterService.clusters.forEachIndexed { i, cluster ->
                val dist = cluster.centroid.dist(centroids[i])
                distMean += dist
            }
            distMean /= nbOfclusters
            if (perf == -1.0) {
                perf = distMean
            } else {
                progressSpeedInd = Math.abs(perf - distMean)
                perf = distMean
            }
            println("perf: $perf, progressSpeedInd: $progressSpeedInd")
        }
        for(j in 0..4) {
            for (i in 0..10) {
                println(ClusterService.clusters[j].objects[i].unNormalize(normalizationVector))
            }
            println("----------------------------------------------------------------------")
        }
    }
}
*/

package generic

import Visualization.recap
import generic.model.*
import java.util.Random


object Engine {


    fun loadData(resourceFile: String): List<DataLine> {
        val stream = DataLine::class.java.getResourceAsStream("/$resourceFile")
        val reader = stream.bufferedReader()
        val linesAsStream = reader.lines()
        val lines = linesAsStream.toArray()
        return lines.map { Engine.readDataLine(it) }
    }

    fun readDataLine(line: Any): DataLine {
        val lineAsString = line as String
        val data = lineAsString.split(", ")
        val fields: MutableList<Field> = DataModel.getVirginFields()
        data.forEachIndexed { i, datum ->
            fields[i].rawValue = datum
        }
        return DataLine(fields)
    }

    fun clusterize(nbOfclusters: Int, threshold: Double, resourceFile: String) {

        var perf = -1.0
        var progressSpeedInd = -1.0

        ClusterService.createClusters(nbOfclusters)
        val data = StringToValuesService.numerize(loadData(resourceFile))
        val normalizationVector = NormalizationService.getMeanObject(data)
        val normalizedData = data.map { datum ->
            datum.normalize(data)
        }

        ClusterService.clusters.forEach { cluster ->
            val random = Random()
            val lineId = random.nextInt(normalizedData.size) + 1
            cluster.objects.add(normalizedData[lineId])
        }
        println("Premiers clusters:")
        ClusterService.clusters.forEach { it.updateCentroid(normalizationVector) }


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
            ClusterService.clusters.forEach { it.updateCentroid(normalizationVector) }
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
        for (j in 0..nbOfclusters-1) {
            for (i in 0..(Math.min(10, ClusterService.clusters[j].objects.size) - 1)) {
                println(ClusterService.clusters[j].objects[i].unNormalize(data))
            }
            println("----------------------------------------------------------------------")
        }
        recap(normalizationVector, data)
    }
}
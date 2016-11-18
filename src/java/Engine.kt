import model.DataLine
import model.NormalizationService
import java.util.Random

object Engine {

    fun loadData(): List<DataLine> {
        val stream = DataLine::class.java.getResourceAsStream("/data.csv")
        val reader = stream.bufferedReader()
        val linesAsStream = reader.lines()
        val lines = linesAsStream.toArray()
        return lines.map { readDataLine(it) }
    }

    fun readDataLine(line: Any): DataLine {
        val lineAsString = line as String
        val data = lineAsString.split(", ")
        return DataLine(data[0].toDouble(), data[2].toDouble(), data[4].toDouble(), data[10].toDouble(),
                data[11].toDouble(), data[12].toDouble())
    }

    fun clusterize(nbOfclusters: Int, threshold: Double) {

        var perf = -1.0
        var progressSpeedInd = -1.0

        ClusterService.createClusters(nbOfclusters)
        val data = loadData()
        val normalizationVector = NormalizationService.getNormalizationObject(data)
        val normalizedData = data.map { datum ->
            datum.normalize(normalizationVector)
        }

        normalizedData.forEach { line ->
            val random = Random()
            val clusterId = random.nextInt(nbOfclusters) + 1
            ClusterService.clusters[clusterId - 1].objects.add(line)
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
        for(i in 0..100){
            println(ClusterService.clusters[1].objects[i].unNormalize(normalizationVector))
        }
    }
}

object ClusterService {

    val clusters = mutableListOf<Cluster>()

    fun createClusters(nbOfClusters: Int) {
        clusters.clear()
        (1..nbOfClusters).mapTo(clusters) { Cluster(it) }
    }
}
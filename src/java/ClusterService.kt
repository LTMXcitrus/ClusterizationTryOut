object ClusterService {

    val clusters = mutableListOf<Cluster>()

    fun createClusters(nbOfClusters: Int) {
        clusters.clear()
        for (i in 1..nbOfClusters){
            clusters.add(Cluster(i))
        }
    }
}
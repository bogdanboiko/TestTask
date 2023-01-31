package com.example.testtaskintegrio.data.remote

import com.example.testtaskintegrio.data.datasource.PlaceDatasource
import com.example.testtaskintegrio.data.remote.entity.PointEntity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import kotlinx.coroutines.tasks.await

class RemoteDatasourceImpl(private val fireStore: FirebaseFirestore) : PlaceDatasource.Remote {
    override suspend fun fetchPoints(): List<PointEntity> {
//        val pointTest = GeoLocation(49.4360412, 32.0672931)
//        val geoHashTest = GeoFireUtils.getGeoHashForLocation(pointTest)

        val documents = fireStore.collection("points").get().await()
        val pointList = mutableListOf<PointEntity>()

        documents.forEach { document ->
            val id = document.id
            val address = document.get("address") as String
            val corpus = document.get("corpus") as Long
            val geoHash = document.get("geoHash") as String
            val coordinates = document.getGeoPoint("coordinates") as GeoPoint
            val properties = document.get("properties") as Map<String, Int>

            pointList.add(
                PointEntity(
                    id,
                    address,
                    corpus,
                    geoHash,
                    coordinates,
                    properties
                )
            )
        }

            return pointList
    }

    override suspend fun putPoint(point: PointEntity) {
        fireStore.collection("points").add(point)
    }
}
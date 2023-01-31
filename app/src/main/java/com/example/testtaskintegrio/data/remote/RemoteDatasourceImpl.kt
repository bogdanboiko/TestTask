package com.example.testtaskintegrio.data.remote

import com.example.testtaskintegrio.data.datasource.PlaceDatasource
import com.example.testtaskintegrio.data.remote.entity.PointEntity
import com.google.firebase.firestore.FirebaseFirestore

class RemoteDatasourceImpl(private val fireStore: FirebaseFirestore) : PlaceDatasource.Remote {
    override fun fetchPoints() {
//        val point = GeoLocation(44.810058, 20.4617586)
//        val geoHash = GeoFireUtils.getGeoHashForLocation(point)
//        fireStore.collection("points").orderBy("geohash").get().addOnSuccessListener {
//            Log.e("e", it.documents.toString())
//        }.addOnFailureListener {
//            Log.e("e", it.message.toString())
//        }
    }

    override suspend fun putPoint(point: PointEntity) {
        fireStore.collection("points").add(point)
    }
}
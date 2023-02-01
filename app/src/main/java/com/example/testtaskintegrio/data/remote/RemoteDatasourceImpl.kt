package com.example.testtaskintegrio.data.remote

import com.example.testtaskintegrio.data.datasource.PlaceDatasource
import com.example.testtaskintegrio.data.remote.entity.PointEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RemoteDatasourceImpl(private val fireStore: FirebaseFirestore) : PlaceDatasource.Remote {
    override suspend fun putPoint(point: PointEntity) {
        fireStore.collection("points").add(point).await()
    }
}
package com.example.testtaskintegrio.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testtaskintegrio.domain.model.PointDomain
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class PointPagingSource(private val queryPointsByGeo: Query) :
    PagingSource<QuerySnapshot, PointDomain>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, PointDomain>): QuerySnapshot? {
        return null
    }

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, PointDomain> {
        return try {
            val currentPage = params.key ?: queryPointsByGeo.limit(5).get().await()

            val lastVisibleProduct = currentPage.documents[currentPage.size() - 1]

            val nextPage = queryPointsByGeo.startAfter(lastVisibleProduct).limit(5).get().await()

            LoadResult.Page(
                data = currentPage.map(::toDomain),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun toDomain(document: QueryDocumentSnapshot) = with(document) {
        PointDomain(
            id = id,
            address = get("address") as String,
            corpus = document.get("corpus") as Long,
            geoHash = document.get("geoHash") as String,
            coordinates = document.getGeoPoint("coordinates") as GeoPoint,
            properties = document.get("properties") as Map<String, Long>
        )
    }
}
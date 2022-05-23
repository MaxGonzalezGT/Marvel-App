package com.android.data.domain.repository

import javax.inject.Inject

class Repository @Inject constructor(
    remoteDataSource: RemoteRepository,
    localDataSource: LocalRepository
) {
    val remote = remoteDataSource
    val local = localDataSource
}
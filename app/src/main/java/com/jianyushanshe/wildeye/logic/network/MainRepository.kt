package com.jianyushanshe.wildeye.logic.network

import com.jianyushanshe.wildeye.logic.dao.MainPageDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Author:wangjianming
 * Time:2020/11/20 15:40
 * Description:MainRepository
 */
class MainRepository private constructor(
    private val mainPageDao: MainPageDao,
    private val wildEyeNetwork: WildEyeNetwork
) {
    suspend fun refreshDiscovery(url: String) = requestDiscovery(url)

    suspend fun refreshHomePageRecommend(url: String) = requestHomePageRecommend(url)

    suspend fun refreshDaily(url: String) = requestDaily(url)

    suspend fun refreshCommunityRecommend(url: String) = requestCommunityRecommend(url)

    suspend fun refreshFollow(url: String) = requestFollow(url)

    suspend fun refreshPushMessage(url: String) = requestPushMessage(url)

    suspend fun refreshHotSearch() = requestHotSearch()

    private suspend fun requestDiscovery(url: String) = withContext(Dispatchers.IO) {
        val response = wildEyeNetwork.fetchDiscovery(url)
        mainPageDao.cacheDiscovery(response)
        response
    }

    private suspend fun requestHomePageRecommend(url: String) = withContext(Dispatchers.IO) {
        val response = wildEyeNetwork.fetchHomePageRecommend(url)
        mainPageDao.cacheHomePageRecommend(response)
        response
    }

    private suspend fun requestDaily(url: String) = withContext(Dispatchers.IO) {
        val response = wildEyeNetwork.fetchDaily(url)
        mainPageDao.cacheDaily(response)
        response
    }

    private suspend fun requestCommunityRecommend(url: String) = withContext(Dispatchers.IO) {
        val response = wildEyeNetwork.fetchCommunityRecommend(url)
        mainPageDao.cacheCommunityRecommend(response)
        response
    }

    private suspend fun requestFollow(url: String) = withContext(Dispatchers.IO) {
        val response = wildEyeNetwork.fetchFollow(url)
        mainPageDao.cacheFollow(response)
        response
    }

    private suspend fun requestPushMessage(url: String) = withContext(Dispatchers.IO) {
        val response = wildEyeNetwork.fetchPushMessage(url)
        mainPageDao.cachePushMessageInfo(response)
        response
    }

    private suspend fun requestHotSearch() = withContext(Dispatchers.IO) {
        val response = wildEyeNetwork.fetchHotSearch()
        mainPageDao.cacheHotSearch(response)
        response
    }

    companion object {
        private var repository: MainRepository? = null

        fun getInstance(dao: MainPageDao, network: WildEyeNetwork): MainRepository {
            if (repository == null) {
                synchronized(MainRepository::class.java) {
                    if (repository == null) {
                        repository = MainRepository(dao, network)
                    }
                }
            }

            return repository!!
        }
    }

}
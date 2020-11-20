package com.jianyushanshe.wildeye.logic.network

import com.jianyushanshe.wildeye.logic.network.api.MainService
import com.jianyushanshe.wildeye.logic.network.api.VideoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Author:wangjianming
 * Time:2020/11/20 14:27
 * Description:WildEyeNetwork
 * 管理所有的网络请求
 */
object WildEyeNetwork {
    private val mainService = ServiceCreator.create(MainService::class.java)
    private val videoService = ServiceCreator.create(VideoService::class.java)
    suspend fun fetchDiscovery(url: String) = mainService.getDiscovery(url).await()
    suspend fun fetchHomePageRecommend(url: String) = mainService.getHomePageRecommend(url).await()

    suspend fun fetchDaily(url: String) = mainService.getDaily(url).await()

    suspend fun fetchCommunityRecommend(url: String) =
        mainService.getCommunityRecommend(url).await()

    suspend fun fetchFollow(url: String) = mainService.gethFollow(url).await()

    suspend fun fetchPushMessage(url: String) = mainService.getPushMessage(url).await()

    suspend fun fetchHotSearch() = mainService.getHotSearch().await()

    suspend fun fetchVideoBeanForClient(videoId: Long) =
        videoService.getVideoBeanForClient(videoId).await()

    suspend fun fetchVideoRelated(videoId: Long) = videoService.getVideoRelated(videoId).await()

    suspend fun fetchVideoReplies(url: String) = videoService.getVideoReplies(url).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body) else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }

}
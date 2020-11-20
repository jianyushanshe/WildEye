package com.jianyushanshe.wildeye.logic.network.api

import com.jianyushanshe.wildeye.logic.model.VideoBeanForClient
import com.jianyushanshe.wildeye.logic.model.VideoRelated
import com.jianyushanshe.wildeye.logic.model.VideoReplies
import com.jianyushanshe.wildeye.logic.network.ServiceCreator
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Author:wangjianming
 * Time:2020/11/20 14:28
 * Description:VideoService
 * 视频相关的api
 */
interface VideoService {
    /**
     * 视频详情-视频信息
     */
    @GET("api/v2/video/{id}")
    fun getVideoBeanForClient(@Path("id") videoId: Long): Call<VideoBeanForClient>

    /**
     * 视频详情-推荐列表
     */
    @GET("api/v4/video/related")
    fun getVideoRelated(@Query("id") videoId: Long): Call<VideoRelated>

    /**
     * 视频详情-评论列表
     */
    @GET
    fun getVideoReplies(@Url url: String): Call<VideoReplies>

    companion object {

        /**
         * 视频详情-评论列表URL
         */
        const val VIDEO_REPLIES_URL = "${ServiceCreator.BASE_URL}api/v2/replies/video?videoId="
    }

}
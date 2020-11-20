package com.jianyushanshe.wildeye.ui.common.holder

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.AUTO_PLAY_VIDEO_AD
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.BANNER
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.BANNER3
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.COLUMN_CARD_LIST
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.FOLLOW_CARD
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.HORIZONTAL_SCROLL_CARD
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.INFORMATION_CARD
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.SPECIAL_SQUARE_CARD_COLLECTION
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.TAG_BRIEFCARD
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.TEXT_CARD_FOOTER2
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.TEXT_CARD_FOOTER3
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.TEXT_CARD_HEADER4
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.TEXT_CARD_HEADER5
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.TEXT_CARD_HEADER7
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.TEXT_CARD_HEADER8
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.TOPIC_BRIEFCARD
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.UGC_SELECTED_CARD_COLLECTION
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.UNKNOWN
import com.jianyushanshe.wildeye.Const.ItemViewType.Companion.VIDEO_SMALL_CARD
import com.jianyushanshe.wildeye.R
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import com.zhpan.bannerview.BannerViewPager

/**
 * Author:wangjianming
 * Time:2020/11/20 10:57
 * Description:ViewHolder
 */

class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)

class TextCardViewHeader4ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle4 = view.findViewById<TextView>(R.id.tvTitle4)
    val ivInto4 = view.findViewById<ImageView>(R.id.ivInto4)
}

class TextCardViewHeader5ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle5 = view.findViewById<TextView>(R.id.tvTitle5)
    val tvFollow = view.findViewById<TextView>(R.id.tvFollow)
    val ivInto5 = view.findViewById<ImageView>(R.id.ivInto5)
}

class TextCardViewHeader7ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle7 = view.findViewById<TextView>(R.id.tvTitle7)
    val tvRightText7 = view.findViewById<TextView>(R.id.tvRightText7)
    val ivInto7 = view.findViewById<ImageView>(R.id.ivInto7)
}

class TextCardViewHeader8ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle8 = view.findViewById<TextView>(R.id.tvTitle8)
    val tvRightText8 = view.findViewById<TextView>(R.id.tvRightText8)
    val ivInto8 = view.findViewById<ImageView>(R.id.ivInto8)
}

class TextCardViewFooter2ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvFooterRightText2 = view.findViewById<TextView>(R.id.tvFooterRightText2)
    val ivTooterInto2 = view.findViewById<ImageView>(R.id.ivTooterInto2)
}

class TextCardViewFooter3ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvRefresh = view.findViewById<TextView>(R.id.tvRefresh)
    val tvFooterRightText3 = view.findViewById<TextView>(R.id.tvFooterRightText3)
    val ivTooterInto3 = view.findViewById<ImageView>(R.id.ivTooterInto3)
}



    fun getItemViewType(type: String, dataType: String) = when (type) {

        "horizontalScrollCard" -> {
            when (dataType) {
                "HorizontalScrollCard" -> HORIZONTAL_SCROLL_CARD
                else -> UNKNOWN
            }
        }
        "specialSquareCardCollection" -> {
            when (dataType) {
                "ItemCollection" -> SPECIAL_SQUARE_CARD_COLLECTION
                else -> UNKNOWN
            }
        }
        "columnCardList" -> {
            when (dataType) {
                "ItemCollection" -> COLUMN_CARD_LIST
                else -> UNKNOWN
            }
        }
        /*"textCard" -> {
            when (item.data.type) {
                "header5" -> TEXT_CARD_HEADER5
                "header7" -> TEXT_CARD_HEADER7
                "header8" -> TEXT_CARD_HEADER8
                "footer2" -> TEXT_CARD_FOOTER2
                "footer3" -> TEXT_CARD_FOOTER3
                else -> UNKNOWN
            }
        }*/
        "banner" -> {
            when (dataType) {
                "Banner" -> BANNER
                else -> UNKNOWN
            }
        }
        "banner3" -> {
            when (dataType) {
                "Banner" -> BANNER3
                else -> UNKNOWN
            }
        }
        "videoSmallCard" -> {
            when (dataType) {
                "VideoBeanForClient" -> VIDEO_SMALL_CARD
                else -> UNKNOWN
            }
        }
        "briefCard" -> {
            when (dataType) {
                "TagBriefCard" -> TAG_BRIEFCARD
                "TopicBriefCard" -> TOPIC_BRIEFCARD
                else -> UNKNOWN
            }
        }
        "followCard" -> {
            when (dataType) {
                "FollowCard" -> FOLLOW_CARD
                else -> UNKNOWN
            }
        }
        "informationCard" -> {
            when (dataType) {
                "InformationCard" -> INFORMATION_CARD
                else -> UNKNOWN
            }
        }
        "ugcSelectedCardCollection" -> {
            when (dataType) {
                "ItemCollection" -> UGC_SELECTED_CARD_COLLECTION
                else -> UNKNOWN
            }
        }
        "autoPlayVideoAd" -> {
            when (dataType) {
                "AutoPlayVideoAdDetail" -> AUTO_PLAY_VIDEO_AD
                else -> UNKNOWN
            }
        }
        else -> UNKNOWN
    }

    private fun getTextCardType(type: String) = when (type) {
        "header4" -> TEXT_CARD_HEADER4
        "header5" -> TEXT_CARD_HEADER5
        "header7" -> TEXT_CARD_HEADER7
        "header8" -> TEXT_CARD_HEADER8
        "footer2" -> TEXT_CARD_FOOTER2
        "footer3" -> TEXT_CARD_FOOTER3
        else -> UNKNOWN
    }

/*
 * Copyright (c) 2020. vipyinzhiwei <vipyinzhiwei@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jianyushanshe.wildeye.util


import com.jianyushanshe.wildeye.logic.dao.EyepetizerDatabase
import com.jianyushanshe.wildeye.logic.network.MainRepository
import com.jianyushanshe.wildeye.logic.network.VideoRepository
import com.jianyushanshe.wildeye.logic.network.WildEyeNetwork

/**
 * 应用程序逻辑控制管理类。
 */
object InjectorUtil {

    private fun getMainPageRepository() = MainRepository.getInstance(EyepetizerDatabase.getMainPageDao(), WildEyeNetwork)

    private fun getViedoRepository() = VideoRepository.getInstance(EyepetizerDatabase.getVideoDao(), WildEyeNetwork)


}
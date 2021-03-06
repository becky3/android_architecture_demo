package com.example.androidarchitecturedemo.api

import com.example.androidarchitecturedemo.entity.GitRepositoryInfo
import com.example.androidarchitecturedemo.repository.ApiResult
import com.example.androidarchitecturedemo.service.OkHttpService
import okhttp3.Call
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class GithubApiSessionClient {

    private val apiService = OkHttpService.shared
    private val targetUrl = "https://api.github.com/search/repositories"

    fun searchRepositories(
        searchWord: String,
        result: ApiResult<List<GitRepositoryInfo>>
    ) {
        val request = Request.Builder().url("${targetUrl}?sort=stars&q=${searchWord}").build()
        val call = apiService.okHttpClient.newCall(request)
        call.enqueue(object : okhttp3.Callback {

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()
                if (body == null) {
                    result.failed(Exception("body is null"))
                    return
                }

                val jsonObject = JSONObject(body)
                println(jsonObject.toString(4))
                val list = GitRepositoryInfo.createListFromJson(jsonObject)
                result.success(list)
            }

            override fun onFailure(call: Call, e: IOException) {
                println(e)
                result.failed(e)
            }
        })

    }

}
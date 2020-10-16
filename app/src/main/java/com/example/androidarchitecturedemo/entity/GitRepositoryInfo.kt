package com.example.androidarchitecturedemo.entity

import org.json.JSONObject
import java.net.URL

class GitRepositoryInfo(
    val id: Int,
    val name: String,
    val description: String,
    val url: URL,
    val starCount: Int,
    val avatarUrl: URL
) {

    companion object {
        fun createListFromJson(jsonObject: JSONObject): List<GitRepositoryInfo> {

            val emptyList = emptyList<GitRepositoryInfo>()
            val items = jsonObject.getJSONArray("items")
            val list : MutableList<GitRepositoryInfo> = mutableListOf()

            (0 until items.length()).forEach { i ->
                val item = items.getJSONObject(i)
                val ownerInfo = item.getJSONObject("owner")
                val avatarUrl = URL(ownerInfo.getString("avatar_url"))
                list.add(
                    GitRepositoryInfo(
                        item.getInt("id"),
                        item.getString("full_name"),
                        item.getString("description"),
                        URL(item.getString("html_url")),
                        item.getInt("stargazers_count"),
                        avatarUrl
                    )
                )
            }

            return list
        }
    }


}
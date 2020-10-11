package com.example.androidarchitecturedemo.entity

import org.json.JSONObject
import java.net.URL

class GitRepositoryInfo(
    val id: Int,
    val name: String,
    val description: String,
    val url: URL,
    val starCount: Int,
    val iconUrl: URL
) {

    val imageUrl: String
        get() = "https://github.com/${name}.png"

    companion object {
        fun createListFromJson(jsonObject: JSONObject): List<GitRepositoryInfo> {

            val emptyList = emptyList<GitRepositoryInfo>()
            val items = jsonObject.getJSONArray("items")
            val list : MutableList<GitRepositoryInfo> = mutableListOf()

            (0 until items.length()).forEach { i ->
                val item = items.getJSONObject(i)
                list.add(
                    GitRepositoryInfo(
                        item.getInt("id"),
                        item.getString("full_name"),
                        item.getString("description"),
                        URL(item.getString("html_url")),
                        item.getInt("stargazers_count"),
                        URL(item.getString("html_url"))
                    )
                )
            }

            return list
        }
    }


}
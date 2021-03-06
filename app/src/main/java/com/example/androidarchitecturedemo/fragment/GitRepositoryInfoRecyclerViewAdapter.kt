package com.example.androidarchitecturedemo.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecturedemo.R
import com.example.androidarchitecturedemo.entity.GitRepositoryInfo

class GitRepositoryInfoRecyclerViewAdapter(

) : RecyclerView.Adapter<GitRepositoryInfoRecyclerViewAdapter.ViewHolder>() {

    var itemClickListener: OnItemClickListener? = null

    private var items: List<GitRepositoryInfo> = emptyList()

    fun updateItems(items: List<GitRepositoryInfo>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val context = holder.imageView.context
        Glide.with(context).load(item.avatarUrl.toString()).into(holder.imageView);
        holder.textViewTitle.text = item.name
        holder.textViewDescription.text = item.description

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClickListener(item)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_view)
        val textViewTitle: TextView = view.findViewById(R.id.text_view_title)
        val textViewDescription: TextView = view.findViewById(R.id.text_view_description)

        override fun toString(): String {
            return super.toString() + " '" + textViewDescription.text + "'"
        }
    }

    interface OnItemClickListener{
        fun onItemClickListener(item: GitRepositoryInfo)
    }
}
package com.example.kosassignment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kosassignment.R
import com.example.kosassignment.data.modal.User


class UserAdapter (private val context: Context) : PagedListAdapter<User, UserAdapter.MyViewHolder>(USER_COMPARATOR)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.getContext())
        val view: View = layoutInflater.inflate(R.layout.user_data_items, parent, false)

      return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.tv_user_name.setText(user?.first_name+""+user?.last_name)
        holder.user_emailTextView.setText(user?.email)
        Glide.with(holder.user_Image.context)
            .load(user?.avatar)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.user_Image)

    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var tv_user_name: TextView
        lateinit var user_emailTextView: TextView
        lateinit var user_Image: ImageView

        init {
            tv_user_name=itemView.findViewById(R.id.tv_user_name)
            user_emailTextView=itemView.findViewById(R.id.user_emailTextView)
            user_Image=itemView.findViewById(R.id.user_Image)

        }
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = newItem == oldItem
        }
    }

}
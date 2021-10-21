package disiiy.khaper.github.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import disiiy.khaper.github.R
import disiiy.khaper.github.activity.DetailActivity
import disiiy.khaper.github.databinding.ItemUserBinding
import disiiy.khaper.github.model.Users

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(){

    private val users = ArrayList<Users>()

    fun setData (user: ArrayList<Users>){
        users.clear()
        users.addAll(user)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerAdapter.FollowerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return FollowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowerAdapter.FollowerViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class FollowerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val itemUserBinding = ItemUserBinding.bind(itemView)

        fun bind (user : Users){
            itemUserBinding.tvItemUsername.text = user.username
            Glide.with(itemView.context)
                .load(user.avatar)
                .apply(RequestOptions().override(55, 55))
                .into(itemUserBinding.ivItemUser)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USERNAME, user.username)
                itemView.context.startActivity(intent)
            }
        }
    }

}
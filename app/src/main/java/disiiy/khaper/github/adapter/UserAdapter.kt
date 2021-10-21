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

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val users = ArrayList<Users>()

    fun setData(user: ArrayList<Users>){
        users.clear()
        users.addAll(user)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemUserBinding = ItemUserBinding.bind(itemView)

        fun bind(users: Users) {
            Glide.with(itemView.context).load(users.avatar)
                .apply(
                    RequestOptions()
                        .override(55, 55)
                )
                .into(itemUserBinding.ivItemUser)

            itemUserBinding.tvItemUsername.text = users.username

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USERNAME, users.username)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}
package disiiy.khaper.github.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import disiiy.khaper.github.R
import disiiy.khaper.github.activity.DetailActivity
import disiiy.khaper.github.adapter.FollowerAdapter
import disiiy.khaper.github.databinding.FragmentFollowerBinding
import disiiy.khaper.github.model.Users
import disiiy.khaper.github.viewmodel.FollowViewModel

class FollowerFragment : Fragment() {

    private var _followerBinding: FragmentFollowerBinding? = null
    private val followerBinding get() = _followerBinding!!
    private lateinit var followViewModel: FollowViewModel
    private lateinit var followerAdapter: FollowerAdapter
    private lateinit var listFollower: ArrayList<Users>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _followerBinding = FragmentFollowerBinding.inflate(inflater, container, false)
        return followerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = activity?.intent?.getStringExtra(DetailActivity.EXTRA_USERNAME) as String

        setAdapter()
        setViewModel(username)
    }

    override fun onResume() {
        super.onResume()
        showProgressBar(true)
    }

    private fun setAdapter() {
        followerAdapter = FollowerAdapter()
        followerAdapter.notifyDataSetChanged()

        followerBinding.rvFollower.apply {
            setHasFixedSize(true)
            adapter = followerAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar(state: Boolean) {
        if (state) {
            followerBinding.pbFollower.visibility = View.VISIBLE
        } else {
            followerBinding.pbFollower.visibility = View.GONE
        }
    }

    private fun setViewModel(username: String) {
        followViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowViewModel::class.java)

        followViewModel.setListFollow(username, "followers", this.requireContext())
        followViewModel.getListFollow().observe(viewLifecycleOwner, { users ->
            if (users != null) {
                listFollower = users
                followerAdapter.setData(listFollower)
                showProgressBar(false)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _followerBinding = null
    }
}

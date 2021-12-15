package ru.grandibambino.remembermeplease.presentation.friends

import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import ru.grandibambino.remembermeplease.R
import ru.grandibambino.remembermeplease.core.BaseFragment
import ru.grandibambino.remembermeplease.databinding.FragmentFriendsBinding

class FriendsFragment : BaseFragment<FragmentFriendsBinding>() {

    private var addFriends: ImageButton? = null

    override fun provideBinding(inflater: LayoutInflater) =
        FragmentFriendsBinding.inflate(inflater)

    override fun setupUI() {
        addFriends = binding?.addFriends
    }

    override fun clickEvent() {
        addFriends?.setOnClickListener { openScreenAddFriend() }
    }

    override fun setObservable() {

    }

    private fun openScreenAddFriend() {
        findNavController().navigate(R.id.action_navigation_friends_to_addFriendsFragment)
    }

}
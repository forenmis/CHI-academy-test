package com.example.chiacademytest.screens.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.chiacademytest.base.BaseFragment
import com.example.chiacademytest.databinding.FragmentUserDetailsBinding
import com.example.chiacademytest.entity.User

class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding>() {
    private val viewModel by viewModels<UserDetailsViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentUserDetailsBinding {
        return FragmentUserDetailsBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = UserDetailsFragmentArgs.fromBundle(requireArguments())
        viewModel.loadUser(args.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            userLD.observe(viewLifecycleOwner) { fillAll(it) }
            exceptionLD.observe(viewLifecycleOwner) { showToast(it.toString()) }
        }
    }

    private fun fillAll(user: User) {
        with(binding) {
            tvName.text = user.name
            tvAge.text = user.age.toString()
            switchIsStudent.isChecked = user.isStudent
        }
    }
}
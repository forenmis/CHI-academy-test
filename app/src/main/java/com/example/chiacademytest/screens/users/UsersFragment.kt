package com.example.chiacademytest.screens.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chiacademytest.R
import com.example.chiacademytest.base.BaseFragment
import com.example.chiacademytest.databinding.FragmentUsersBinding
import kotlinx.coroutines.launch

class UsersFragment : BaseFragment<FragmentUsersBinding>() {

    private lateinit var usersAdapter: UsersAdapter

    private val viewModel by viewModels<UsersViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentUsersBinding {
        return FragmentUsersBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersAdapter = UsersAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMenu()
        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }
        setFragmentResultListener(RESULT_CREATE_USER) { _, _ ->
            viewModel.loadUsers()
            showToast(R.string.success)
        }

        lifecycleScope.launch { viewModel.usersFlow.collect { usersAdapter.updateItems(it) } }
        lifecycleScope.launch { viewModel.exceptionFlow.collect { showToast(it.toString()) } }

        usersAdapter.onStudentChange = { id, isStudent -> viewModel.changeIsStudent(id, isStudent) }
        usersAdapter.onUserClick = { id ->
            val action =
                UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(id)
            findNavController().navigate(action)
        }
    }

    private fun toCreateUser() {
        val action = UsersFragmentDirections.actionUsersFragmentToCreateUserFragment()
        findNavController().navigate(action)
    }

    private fun initMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.users_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.itemAdd -> {
                        toCreateUser()
                    }
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    companion object {
        const val RESULT_CREATE_USER = "result_create_user"
    }
}
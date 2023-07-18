package com.example.chiacademytest.screens.create_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.chiacademytest.base.BaseFragment
import com.example.chiacademytest.databinding.FragmentCreateUserBinding
import com.example.chiacademytest.screens.users.UsersFragment.Companion.RESULT_CREATE_USER
import com.google.android.material.datepicker.MaterialDatePicker

class CreateUserFragment : BaseFragment<FragmentCreateUserBinding>() {

    private val viewModel by viewModels<CreateUserViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCreateUserBinding {
        return FragmentCreateUserBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            etName.doAfterTextChanged { viewModel.changeName(it.toString()) }
            etAge.setOnClickListener { showDatePicker() }
            btAdd.setOnClickListener { viewModel.createUser() }
        }
        with(viewModel) {
            exceptionLD.observe(viewLifecycleOwner) { showToast(it.toString()) }
            isDataFilledLD.observe(viewLifecycleOwner) { binding.btAdd.isEnabled = it }
            dateBirthLD.observe(viewLifecycleOwner) { binding.etAge.setText(it) }
            savedLD.observe(viewLifecycleOwner) {
                if (it) setFragmentResult(RESULT_CREATE_USER, Bundle.EMPTY)
                findNavController().popBackStack()
            }
        }
    }

    private fun showDatePicker() {
        MaterialDatePicker.Builder
            .datePicker()
            .setTitleText("Select date")
            .build()
            .also { datePicker ->
                datePicker.addOnPositiveButtonClickListener { viewModel.changeDate(it) }
            }
            .show(parentFragmentManager, "tag_date")
    }
}
package ru.grandibambino.remembermeplease.presentation.add_friend

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.grandibambino.remembermeplease.R
import ru.grandibambino.remembermeplease.appComponent
import ru.grandibambino.remembermeplease.common.extention.afterTextChanged
import ru.grandibambino.remembermeplease.core.BaseFragment
import ru.grandibambino.remembermeplease.databinding.FragmentAddFriendsBinding
import ru.grandibambino.remembermeplease.presentation.add_friend.CheckNameOrSecondName.NAME
import ru.grandibambino.remembermeplease.presentation.add_friend.CheckNameOrSecondName.SECOND_NAME
import ru.grandibambino.remembermeplease.presentation.add_friend.viewmodel.AddFriendViewModel
import ru.grandibambino.remembermeplease.presentation.add_friend.viewmodel.AddFriendViewModelFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class AddFriendFragment : BaseFragment<FragmentAddFriendsBinding>() {

    private val viewModel: AddFriendViewModel by viewModels {
        factory.create()
    }

    @Inject
    lateinit var factory: AddFriendViewModelFactory.Factory

    private var bottomSheetDialog: BottomSheetDialog? = null

    override fun provideBinding(inflater: LayoutInflater) =
        FragmentAddFriendsBinding.inflate(inflater)

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun setupUI() {
        openBirthdaySheetDialog()
        bottomSheetDialog = BottomSheetDialog(
            requireContext(), R.style.BottomSheetDialogTheme
        )
    }

    override fun setObservable() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.friend.collect {
                    showFirstName(it.firstName)
                    showLastName(it.secondName)
                    showBirthday(it.dataBirthday)
                }
            }
        }
    }

    override fun clickEvent() {
        with(binding) {
            this?.let {
                firstName.setOnClickListener { openBottomSheetDialog(NAME) }
                lastName.setOnClickListener { openBottomSheetDialog(SECOND_NAME) }
                add.setOnClickListener { saveFriend() }
                checkerGender()
                checkKinship()
            }
        }
    }

    private fun saveFriend() {
        viewModel.clickButtonAddFriend()
    }

    private fun openBirthdaySheetDialog() {
        binding?.birthday?.setOnClickListener {

            val bottomSheet =
                createBottomSheet(R.layout.item_birthday_datapicker, R.id.bottom_sheet)

            val datePicker = bottomSheet.findViewById<DatePicker>(R.id.birthday_data_picker)

            val day = datePicker.dayOfMonth
            val month = datePicker.month
            val year = datePicker.year
            bottomSheet.findViewById<MaterialButton>(R.id.save_data_birthday)
                .setOnClickListener {
                    viewModel.setBirthday(day, month, year)
                    bottomSheetDialog?.dismiss()
                }

            bottomSheetDialog?.setContentView(bottomSheet)
            bottomSheetDialog?.show()
        }
    }

    private fun checkerGender() {
        binding?.gender?.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.male -> viewModel.setGender(getString(R.string.male_radio_button))
                R.id.female -> viewModel.setGender(getString(R.string.female_radio_button))
            }
        }
    }

    private fun openBottomSheetDialog(checkNameOrSecondName: CheckNameOrSecondName) {
        val bottomSheet =
            createBottomSheet(R.layout.item_edit_text, R.id.bottom_sheet_input_text)

        val editText = bottomSheet.findViewById<EditText>(R.id.input_text)
        when (checkNameOrSecondName) {
            NAME -> {
                editText.hint = getString(R.string.input_name)
                editText.afterTextChanged {
                    viewModel.setFirstName(it)
                }
            }
            SECOND_NAME -> {
                editText.hint = getString(R.string.input_second_name)
                editText.afterTextChanged {
                    viewModel.setLastName(it)
                }
            }
        }

        bottomSheet.findViewById<MaterialButton>(R.id.save)
            .setOnClickListener {
                bottomSheetDialog?.dismiss()
            }
        bottomSheetDialog?.setContentView(bottomSheet)
        bottomSheetDialog?.show()
    }

    private fun createBottomSheet(
        layout: Int,
        bottomSheet: Int
    ) = LayoutInflater.from(context).inflate(
        layout,
        requireActivity().findViewById(bottomSheet)
    )

    private fun showFirstName(name: String?) {
        binding?.firstName?.text = name ?: getString(R.string.enter_name)
    }

    private fun showLastName(lastName: String?) {
        binding?.lastName?.text = lastName ?: getString(R.string.enter_last_name)
    }

    private fun showBirthday(birthday: LocalDate?) {
        val date = birthday?.format(DateTimeFormatter.ISO_LOCAL_DATE)
        binding?.birthday?.text = date ?: getString(R.string.enter_birthday)
    }

    private fun checkKinship() {
        val kinship = resources.getStringArray(R.array.kinship_list)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, kinship)

        binding?.checkKinship?.setAdapter(adapter)

        binding?.checkKinship?.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                viewModel.setKinship(parent.getItemAtPosition(position).toString())
            }
    }

    companion object {
        const val DATE_PATTERN = "dd.mm.yyyy"
    }
}

enum class CheckNameOrSecondName {
    NAME,
    SECOND_NAME
}
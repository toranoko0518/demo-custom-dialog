package io.github.toranoko0518.demo.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import io.github.toranoko0518.demo.dialog.databinding.FragmentDialogBinding

class CustomDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.takeIf { it != null }?.window
            ?.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
    }

    override fun onStart() {
        super.onStart()
        dialog.takeIf { it != null }?.window
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.takeIf { it != null }?.window
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun init() {
        val adapter = RowItemAdapter()

        binding.apply {
            recycler.adapter = adapter
            close.setOnClickListener { dismiss() }
            root.setOnClickListener { dismiss() }
        }

        val list = listOf(
            Row(id = 0, name = "James Smith", city = "Los Angeles"),
            Row(id = 1, name = "John Johnson", city = "Houston"),
            Row(id = 2, name = "Robert Williams", city = "Jacksonville"),
            Row(id = 3, name = "Michael Brown", city = "New York"),
            Row(id = 4, name = "William Jones", city = "Chicago")
        )
        adapter.submitList(list)
    }

    companion object {

        private const val DIALOG_TAG = "custom_dialog"

        fun show(activity: FragmentActivity) =
            newInstance().show(activity.supportFragmentManager, DIALOG_TAG)

        private fun newInstance() = CustomDialogFragment()
    }
}

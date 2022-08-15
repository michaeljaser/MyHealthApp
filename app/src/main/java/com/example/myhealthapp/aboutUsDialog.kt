package com.example.myhealthapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.*

class aboutUsDialog : DialogFragment(R.layout.aboutus_dialog){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val okBtn : Button = view.findViewById(R.id.btnDialogOK)

        okBtn.setOnClickListener{
            dismiss()
        }
    }
}
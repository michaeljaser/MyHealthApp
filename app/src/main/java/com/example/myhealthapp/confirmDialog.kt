package com.example.myhealthapp

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.confirm_dialog.view.*

class confirmDialog : DialogFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.confirm_dialog, container, false)
        rootView.btnDialogNo.setOnClickListener {
            dismiss()
        }

        rootView.btnDialogYes.setOnClickListener {
            val act:Settings = activity as Settings
            act.recieveAction("Yes")
            dismiss()
/*            val context = this
            var db = DBHandler(this.context)

            db.deleteUserData()
            Toast.makeText(this.context, "Deleted", Toast.LENGTH_SHORT).show()
            dismiss()*/
        }
        return rootView
    }
}
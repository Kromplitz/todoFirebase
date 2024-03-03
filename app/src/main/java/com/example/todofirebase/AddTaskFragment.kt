package com.example.todofirebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class AddTaskFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.input_fragment_layout, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subjectField: EditText = view.findViewById(R.id.subjectInputField)
        val todoField: EditText = view.findViewById(R.id.todoInputField)
        val button: Button = view.findViewById(R.id.addButton)
        val database = FirebaseDatabase.getInstance(
            "https://todofirebase-f6945-default-rtdb.europe-west1.firebasedatabase.app/"
        )
        val account =
            GoogleSignIn.getLastSignedInAccount(requireContext())
        button.setOnClickListener {
            val holder = Holder()
            holder.subject = subjectField.text.toString()
            holder.todo = todoField.text.toString()
            val target = database.reference.child(
                "unknown_account"
            )
                .child("tasks")
                .child(UUID.randomUUID().toString())
            target.setValue(holder)
                .addOnCompleteListener {
                    parentFragmentManager.popBackStack()
                }
        }
    }
}
class Holder {
    var subject: String? = ""
    var todo: String? = ""
}


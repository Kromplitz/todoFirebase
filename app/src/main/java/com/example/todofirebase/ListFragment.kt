
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todofirebase.R
import com.example.todofirebase.TaskListAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ListFragment : Fragment() {
    private var listView:RecyclerView? = null
    private var account: GoogleSignInAccount? = null
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance(
        "https://todofirebase-f6945-default-rtdb.europe-west1.firebasedatabase.app/")
    private var target: DatabaseReference? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab: FloatingActionButton = view.findViewById(R.id.fab)
       val list = view.findViewById<RecyclerView>(R.id.list)
        account = GoogleSignIn.getLastSignedInAccount(requireContext())
        target = database.reference
            .child(account?.id ?: "unknown_account").child("tasks")
        fab.setOnClickListener {
            val activity = requireActivity() as View.OnClickListener
            activity.onClick(fab)
        }
        target?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val taskList = mutableListOf<String>()
                if (snapshot.exists()) {
                    snapshot.children.forEach {
                        val task = it?.getValue(String::class.java) ?: ""
                        taskList.add(task)
                    }

                    listView?.layoutManager = LinearLayoutManager(requireContext())
                    val adapter = TaskListAdapter()
                    listView?.adapter = adapter





    }
}
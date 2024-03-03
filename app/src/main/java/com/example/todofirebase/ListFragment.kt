
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todofirebase.Holder
import com.example.todofirebase.R
import com.example.todofirebase.TaskListAdapter
import com.example.todofirebase.onAddTaskFragmentListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ListFragment : Fragment() {
    private var listView: RecyclerView? = null
    private var account: GoogleSignInAccount? = null
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance(
        "https://todofirebase-f6945-default-rtdb.europe-west1.firebasedatabase.app/"
    )
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
        listView = view.findViewById(R.id.list)
        //account = GoogleSignIn.getLastSignedInAccount(requireContext())
        target = database.reference
            .child("unknown_account").child("tasks")
        fab.setOnClickListener {
            val activity = requireActivity() as onAddTaskFragmentListener
            activity.onClick()
        }
        listView?.layoutManager = LinearLayoutManager(requireContext())
        target?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val taskList = mutableListOf<Holder>()
                if (snapshot.exists()) {
                    snapshot.children.forEach {
                        val task = it.getValue(Holder::class.java)?:Holder()
                        taskList.add(task)
                    }
                    val adapter = TaskListAdapter(taskList)
                    listView?.adapter = adapter                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}
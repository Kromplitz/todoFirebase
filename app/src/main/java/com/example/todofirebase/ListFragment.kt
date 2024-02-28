
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.todofirebase.R
import com.google.android.gms.auth.api.signin.GoogleSignIn

class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = view.findViewById<TextView>(R.id.content)
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        account?.let {
            content.text = "${it.displayName} ${it.email}"
        }
    }
}
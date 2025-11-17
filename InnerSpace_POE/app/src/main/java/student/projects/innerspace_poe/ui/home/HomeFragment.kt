package student.projects.innerspace_poe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import  student.projects.innerspace_poe.R
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var txtDate: TextView
    private lateinit var txtWelcome: TextView
    private lateinit var txtDailyQuote: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize views
        txtDate = root.findViewById(R.id.txtDate)
        txtWelcome = root.findViewById(R.id.txtWelcome)
        txtDailyQuote = root.findViewById(R.id.txtDailyQuote)

        // Set current date
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        txtDate.text = sdf.format(Date())

        // Example: dynamically set welcome text (replace with actual user)
        txtWelcome.text = "Welcome Back, Talia"

        // Set daily quote (replace with actual logic to fetch a quote)
        txtDailyQuote.text = "“Every day is a chance to grow.”"

        // Set click listeners for feature cards
        root.findViewById<View>(R.id.btnDailyEntry).setOnClickListener {
            Toast.makeText(requireContext(), "Daily Entry clicked", Toast.LENGTH_SHORT).show()
            // Navigate to Daily Entry screen
        }

        root.findViewById<View>(R.id.btnPastEntries).setOnClickListener {
            Toast.makeText(requireContext(), "Past Entries clicked", Toast.LENGTH_SHORT).show()
            // Navigate to Past Entries screen
        }

        root.findViewById<View>(R.id.btnQuote).setOnClickListener {
            Toast.makeText(requireContext(), "Motivational Quote clicked", Toast.LENGTH_SHORT).show()
            // Navigate to Quote screen or show new quote
        }

        return root
    }
}

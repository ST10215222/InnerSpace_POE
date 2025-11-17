package student.projects.innerspace_poe.ui.add_journal

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import student.projects.innerspace_poe.R
import student.projects.innerspace_poe.databinding.FragmentAddJournalBinding

class AddJournalFragment : Fragment() {

    // Gallery picker
    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            binding.journalScroll.background = Drawable.createFromStream(
                requireContext().contentResolver.openInputStream(it),
                it.toString()
            )
        }
    }

    // Camera capture
    private val takePhotoLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        bitmap?.let {
            binding.journalScroll.background = BitmapDrawable(resources, it)
        }
    }

    private var _binding: FragmentAddJournalBinding? = null
    private val binding get() = _binding!!

    private val PICK_IMAGE = 100
    private val viewModel: AddJournalViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddJournalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Save entry
        binding.btnSave.setOnClickListener {
            val entryText = binding.journalEntry.text.toString()
            if (entryText.isNotBlank()) {
                viewModel.saveEntry(entryText)
                Toast.makeText(requireContext(), "Entry saved!", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Entry is empty.", Toast.LENGTH_SHORT).show()
            }
        }

        // Close button
        binding.topAppBar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Predefined wallpapers
        binding.btnWallpaper.setOnClickListener {
            val wallpapers = arrayOf("Soft Gradient", "Calm Blue", "Warm Sunset")
            AlertDialog.Builder(requireContext())
                .setTitle("Choose Wallpaper")
                .setItems(wallpapers) { _, which ->
                    when (which) {
                        0 -> binding.journalScroll.setBackgroundResource(R.drawable.wallpaper_gradient)
                        1 -> binding.journalScroll.setBackgroundResource(R.drawable.wallpaper_blue)
                        2 -> binding.journalScroll.setBackgroundResource(R.drawable.wallpaper_sunset)
                    }
                }
                .show()
        }

        // Font size customization
        binding.btnFontSize.setOnClickListener {
            val sizes = arrayOf("Small", "Medium", "Large")
            AlertDialog.Builder(requireContext())
                .setTitle("Choose Font Size")
                .setItems(sizes) { _, which ->
                    when (which) {
                        0 -> binding.journalEntry.textSize = 14f
                        1 -> binding.journalEntry.textSize = 18f
                        2 -> binding.journalEntry.textSize = 22f
                    }
                }
                .show()

            val styles = arrayOf("Normal", "Italic", "Bold")
            AlertDialog.Builder(requireContext())
                .setTitle("Choose Font Style")
                .setItems(styles) { _, which ->
                    when (which) {
                        0 -> binding.journalEntry.setTypeface(null, Typeface.NORMAL)
                        1 -> binding.journalEntry.setTypeface(null, Typeface.ITALIC)
                        2 -> binding.journalEntry.setTypeface(null, Typeface.BOLD)
                    }
                }
                .show()
        }

        // Camera/gallery
        binding.btnCamera.setOnClickListener {
            val options = arrayOf("Pick from Gallery", "Take Photo")
            AlertDialog.Builder(requireContext())
                .setTitle("Attach Photo")
                .setItems(options) { _, which ->
                    when (which) {
                        0 -> pickImageLauncher.launch("image/*")
                        1 -> takePhotoLauncher.launch(null)
                    }
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

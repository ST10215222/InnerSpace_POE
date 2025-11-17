package student.projects.innerspace_poe.ui.add_journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddJournalViewModel : ViewModel() {

    private val _entryText = MutableLiveData<String>()
    val entryText: LiveData<String> get() = _entryText

    fun saveEntry(text: String) {
        _entryText.value = text
        // TODO: Persist to local database or file
    }
}
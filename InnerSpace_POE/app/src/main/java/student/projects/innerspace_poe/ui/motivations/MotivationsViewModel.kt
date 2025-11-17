package student.projects.innerspace_poe.ui.motivations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MotivationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is motivations Fragment"
    }
    val text: LiveData<String> = _text
}
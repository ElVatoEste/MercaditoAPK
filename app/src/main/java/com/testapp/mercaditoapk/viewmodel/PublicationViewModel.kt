import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.mercaditoapk.model.Publication
import com.testapp.mercaditoapk.model.PublicationDTO
import com.testapp.mercaditoapk.repository.RepositoryPublication
import kotlinx.coroutines.launch

class PublicationViewModel: ViewModel() {

    private val repository = RepositoryPublication()

    private val _publications = MutableLiveData<List<Publication>>()
    val publications: LiveData<List<Publication>> get() = _publications

    private val _recentPublications = MutableLiveData<List<Publication>>()
    val recentPublications: LiveData<List<Publication>> get() = _recentPublications

    private val _featuredPublications = MutableLiveData<List<Publication>>()
    val featuredPublications: LiveData<List<Publication>> get() = _featuredPublications

    private val _randomPublications = MutableLiveData<List<Publication>>()
    val randomPublications: LiveData<List<Publication>> get() = _randomPublications

    private val _publication = MutableLiveData<Publication?>()
    val publication: LiveData<Publication?> get() = _publication

    private val _recentPublicationsId = MutableLiveData<List<Long>>()
    val recentPublicationsId: LiveData<List<Long>> get() = _recentPublicationsId

    private val _resultMessage = MutableLiveData<String>()
    val resultMessage: LiveData<String> get() = _resultMessage

    init {
        getRecentPublicationsId()
    }

    fun getRecentPublicationsId() {
        viewModelScope.launch {
            val result = repository.getRecentPublicationsId()
            result.onSuccess {
                _recentPublicationsId.value = it
            }.onFailure {
                _resultMessage.value = it.message
            }
        }
    }

    fun getAllPublications() {
        viewModelScope.launch {
            val result = repository.getAllPublications()
            result.onSuccess {
                _publications.value = it
            }.onFailure {
                _resultMessage.value = it.message
            }
        }
    }

    fun getRandomFeaturedPublications() {
        viewModelScope.launch {
            val result = repository.getRandomFeaturedPublications()
            result.onSuccess {
                _featuredPublications.value = it
            }.onFailure {
                _resultMessage.value = it.message
            }
        }

        fun getRecentPublications() {
            viewModelScope.launch {
                val result = repository.getRecentPublications()
                result.onSuccess {
                    _recentPublications.value = it
                }.onFailure {
                    _resultMessage.value = it.message
                }
            }
        }

        fun getRecentPublicationsId() {
            viewModelScope.launch {
                val result = repository.getRecentPublicationsId()
                result.onSuccess {
                    _recentPublicationsId.value = it
                }.onFailure {
                    _resultMessage.value = it.message
                }
            }

            fun getFeaturedPublications() {
                viewModelScope.launch {
                    val result = repository.getRandomFeaturedPublications()
                    result.onSuccess {
                        _featuredPublications.value = it
                    }.onFailure {
                        _resultMessage.value = it.message
                    }
                }
            }

            fun getPublicationById(id: Long) {
                viewModelScope.launch {
                    val result = repository.getPublicationById(id)
                    result.onSuccess {
                        _publication.value = it
                    }.onFailure {
                        _resultMessage.value = it.message
                    }
                }
            }

            fun createPublication(publicationDTO: PublicationDTO) {
                viewModelScope.launch {
                    val result = repository.createPublication(publicationDTO)
                    result.onSuccess {
                        _resultMessage.value = it
                        getAllPublications()
                    }.onFailure {
                        _resultMessage.value = it.message
                    }
                }
            }

            fun updatePublication(publicationDTO: PublicationDTO) {
                viewModelScope.launch {
                    val result = repository.updatePublication(publicationDTO)
                    result.onSuccess {
                        _resultMessage.value = it
                        getAllPublications()
                    }.onFailure {
                        _resultMessage.value = it.message
                    }
                }
            }

            fun deletePublication(id: Long) {
                viewModelScope.launch {
                    val result = repository.deletePublication(id)
                    result.onSuccess {
                        _resultMessage.value = it
                        getAllPublications()
                    }.onFailure {
                        _resultMessage.value = it.message
                    }
                }
            }
        }
    }
}
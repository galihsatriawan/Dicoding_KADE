package id.shobrun.footballleague.ui.search.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.repository.TeamRepository
import id.shobrun.footballleague.utils.AbsentLiveData
import id.shobrun.footballleague.utils.wrapEspressoIdlingResource
import javax.inject.Inject

class SearchTeamsViewModel @Inject constructor(repository: TeamRepository) : ViewModel() {
    val teamLiveData: LiveData<Resource<List<Team>>>
    private val filterLiveData: MutableLiveData<String> = MutableLiveData()


    init {
        teamLiveData=
            filterLiveData.switchMap {
                filterLiveData.value?.let {
                    wrapEspressoIdlingResource {
                        repository.loadSearchTeams(it)
                    }
                } ?: AbsentLiveData.create()
            }
    }

    fun postFilter(qry: String) {
        wrapEspressoIdlingResource {
            this.filterLiveData.postValue(qry)
        }
    }
}

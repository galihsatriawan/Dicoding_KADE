package id.shobrun.footballleague.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import id.shobrun.footballleague.databinding.ItemTeamBinding
import id.shobrun.footballleague.models.entity.Team
import org.jetbrains.anko.AnkoLogger
import timber.log.Timber

class RecyclerTeamsAdapter(private var items: List<Team>) :
    RecyclerView.Adapter<RecyclerTeamsAdapter.TeamViewHolder>(), AnkoLogger {
    companion object {
        val TAG = RecyclerEventsAdapter.javaClass.name
    }

    private lateinit var itemListener: (Team) -> Unit
    fun setItemListener(listener: (Team) -> Unit) {
        this.itemListener = listener
    }

    fun setItems(items: List<Team>?) {
        if (items != null) {
            this.items = items
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemTeamBinding.inflate(layoutInflater, parent, false)

        val view = TeamViewHolder(itemBinding)
        view.listen { pos ->
            itemListener(this.items[pos])
            Timber.d("$TAG id = ${this.items[pos].idLeague}")
        }
        return view
    }


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition)
        }
        return this
    }

    class TeamViewHolder(private val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root), AnkoLogger {
        private val viewModel = TeamViewModel()

        fun bind(team: Team) {
            binding.vm = viewModel
            viewModel.bind(team)
            binding.executePendingBindings()

        }
    }

    class TeamViewModel : ViewModel() {

        private var _tv_team_name: MutableLiveData<String> = MutableLiveData()
        var tv_team_name: LiveData<String> = _tv_team_name

        private var _tv_team_badge: MutableLiveData<String> = MutableLiveData()
        var tv_team_badge: LiveData<String> = _tv_team_badge

        private var _tv_team_desc: MutableLiveData<String> = MutableLiveData()
        var tv_team_desc: LiveData<String> = _tv_team_desc


        fun bind(team: Team) {
            _tv_team_name.value = team.teamName
            _tv_team_badge.value = team.teamBadge
            _tv_team_desc.value = "${team.descriptionEN?.subSequence(0,30)} [Read more...]"
        }
    }
}
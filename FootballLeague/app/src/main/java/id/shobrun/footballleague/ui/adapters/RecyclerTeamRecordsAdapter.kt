package id.shobrun.footballleague.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

import id.shobrun.footballleague.databinding.ItemTeamRecordBinding
import id.shobrun.footballleague.models.entity.TeamRecord


class RecyclerTeamRecordsAdapter(private var teamRecords: List<TeamRecord>) : RecyclerView.Adapter<RecyclerTeamRecordsAdapter.RowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTeamRecordBinding.inflate(inflater,parent,false)
        return RowViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        holder.bind(teamRecords[position])
    }
    fun setItems(items: List<TeamRecord>?){
        if(items!=null){

            this.teamRecords = items.sortedBy { it.rank }
            notifyDataSetChanged()
        }
    }
    override fun getItemCount(): Int {
        return teamRecords.size // one more to add header row
    }

    inner class RowViewHolder(val binding:ItemTeamRecordBinding ) : RecyclerView.ViewHolder(binding.root){
        val viewModel = TeamRecordViewModel()
        fun bind(teamRecord: TeamRecord){
            with(binding){
                vm = viewModel
                viewModel.bind(teamRecord)
                executePendingBindings()
            }
        }
    }

    class TeamRecordViewModel: ViewModel(){
        private val _tv_rank = MutableLiveData<String>()
        val tv_rank = _tv_rank

        private val _img_team = MutableLiveData<String>()
        val img_team = _img_team

        private val _tv_name_team = MutableLiveData<String>()
        val tv_name_team = _tv_name_team

        private val _tv_played = MutableLiveData<String>()
        val tv_played = _tv_played

        private val _tv_win = MutableLiveData<String>()
        val tv_win = _tv_win

        private val _tv_draw = MutableLiveData<String>()
        val tv_draw = _tv_draw

        private val _tv_lose = MutableLiveData<String>()
        val tv_lose = _tv_lose

        fun bind(teamRecord: TeamRecord){
            _tv_rank.value = "${teamRecord.rank}"
            _tv_name_team.value = teamRecord.teamName
            _tv_played.value="${teamRecord.played}"
            _tv_win.value = "${teamRecord.win}"
            _tv_draw.value = "${teamRecord.draw}"
            _tv_lose.value = "${teamRecord.loss}"
        }

    }
}
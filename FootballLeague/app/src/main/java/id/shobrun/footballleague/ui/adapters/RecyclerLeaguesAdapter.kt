package id.shobrun.footballleague.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import id.shobrun.footballleague.databinding.ItemLeagueBinding
import id.shobrun.footballleague.models.entity.League
import org.jetbrains.anko.*
import timber.log.Timber

class RecyclerLeaguesAdapter(private var items: List<League>) :
    RecyclerView.Adapter<RecyclerLeaguesAdapter.LeagueViewHolder>(),AnkoLogger{
    companion object{
        val TAG = RecyclerLeaguesAdapter.javaClass.name
    }
    private lateinit var itemListener: (League) -> Unit
    fun setItemListener(listener: (League) -> Unit) {
        this.itemListener = listener
    }

    fun setItems(items: List<League>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemLeagueBinding.inflate(layoutInflater,parent,false)

        val view = LeagueViewHolder(itemBinding)
        view.listen { pos->
            itemListener(this.items[pos])
            Timber.d("$TAG id = ${this.items[pos]._id}")
        }
            return view
    }


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition)
        }
        return this
    }
    class LeagueViewHolder(private val binding: ItemLeagueBinding   ) :
        RecyclerView.ViewHolder(binding.root),AnkoLogger  {
        private val viewModel = LeagueViewModel()

        fun bind(league: League) {
            league.name = "${league.description.subSequence(0,10)} [...]"
            binding.vm = viewModel
            viewModel.bind(league)
            binding.executePendingBindings()

        }
    }

    class LeagueViewModel: ViewModel(){
        var _img_league  : MutableLiveData<Int> = MutableLiveData()
        var img_league : LiveData<Int> = _img_league

        var _tv_league_name : MutableLiveData<String> = MutableLiveData()
        var tv_league_name : LiveData<String> = _tv_league_name

        fun bind(league : League){
            _img_league.value = league.banner
            _tv_league_name.value = league.name
        }
    }
}
package id.shobrun.footballleague.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import id.shobrun.footballleague.R
import id.shobrun.footballleague.databinding.ItemLeagueBinding
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.ui.adapters.LeagueViewModel
import org.jetbrains.anko.*

class RecyclerLeaguesAdapter(private var items: List<League>) :
    RecyclerView.Adapter<RecyclerLeaguesAdapter.LeagueViewHolder>(),AnkoLogger{

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
            itemListener(items[pos])
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
            viewModel.bind(league)
            binding.vm = viewModel
            binding.executePendingBindings()

        }
    }


}
package id.shobrun.footballleague.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import id.shobrun.footballleague.databinding.ItemLeagueBinding
import id.shobrun.footballleague.extensions.gone
import id.shobrun.footballleague.extensions.visible
import id.shobrun.footballleague.models.entity.League
import kotlinx.android.synthetic.main.item_league.view.*
import org.jetbrains.anko.AnkoLogger
import timber.log.Timber


class RecyclerLeaguesAdapter(private var items: List<League>) :
    RecyclerView.Adapter<RecyclerLeaguesAdapter.LeagueViewHolder>(), AnkoLogger {
    companion object {
        val TAG = RecyclerLeaguesAdapter.javaClass.name
    }

    private lateinit var detailListener: (League) -> Unit
    private lateinit var matchListener: (League) -> Unit
    fun setDetailListener(listener: (League) -> Unit) {
        this.detailListener = listener
    }

    fun setMatchListener(listener: (League) -> Unit) {
        this.matchListener = listener
    }

    fun setItems(items: List<League>) {
        this.items = items
        notifyDataSetChanged()
    }

    private fun expandableView(v: View) {
        if (v.isVisible)
            v.gone()
        else v.visible()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemLeagueBinding.inflate(layoutInflater, parent, false)


        val view = LeagueViewHolder(itemBinding)

        with(view.itemView) {
            this.setOnClickListener {
                Timber.d("$TAG Click item")
                expandableView(this.container_btn_league)
            }
            btn_detail_league.setOnClickListener {
                Timber.d("$TAG Click Detail")
                detailListener(items[view.adapterPosition])
            }
            btn_view_events.setOnClickListener {
                Timber.d("$TAG Click Event")
                matchListener(items[view.adapterPosition])
            }
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

    class LeagueViewHolder(private val binding: ItemLeagueBinding) :
        RecyclerView.ViewHolder(binding.root), AnkoLogger {
        private val viewModel = LeagueViewModel()

        fun bind(league: League) {
            binding.vm = viewModel
            viewModel.bind(league)
            binding.executePendingBindings()

        }
    }

    class LeagueViewModel : ViewModel() {
        private var _img_league: MutableLiveData<Int> = MutableLiveData()
        var img_league: LiveData<Int> = _img_league

        private var _tv_league_name: MutableLiveData<String> = MutableLiveData()
        var tv_league_name: LiveData<String> = _tv_league_name

        fun bind(league: League) {
            _img_league.value = league.banner
            _tv_league_name.value = league.name
        }
    }

}
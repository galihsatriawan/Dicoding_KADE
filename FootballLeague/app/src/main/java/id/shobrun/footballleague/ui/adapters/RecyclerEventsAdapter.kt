package id.shobrun.footballleague.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import id.shobrun.footballleague.databinding.ItemEventBinding
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.entity.League
import org.jetbrains.anko.AnkoLogger
import timber.log.Timber


class RecyclerEventsAdapter(private var items: List<Event>) :
    RecyclerView.Adapter<RecyclerEventsAdapter.EventViewHolder>(), AnkoLogger {
    companion object{
        val TAG = RecyclerEventsAdapter.javaClass.name
    }
    private lateinit var itemListener: (Event) -> Unit
    fun setItemListener(listener: (Event) -> Unit) {
        this.itemListener = listener
    }

    fun setItems(items: List<Event>?) {
        if(items!=null){
            this.items = items
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemEventBinding.inflate(layoutInflater,parent,false)

        val view = EventViewHolder(itemBinding)
        view.listen { pos->
            itemListener(this.items[pos])
            Timber.d("$TAG id = ${this.items[pos].idLeague}")
        }
        return view
    }


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition)
        }
        return this
    }
    class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root), AnkoLogger {
        private val viewModel = EventViewModel()

        fun bind(event: Event) {
            binding.vm = viewModel
            viewModel.bind(event)
            binding.executePendingBindings()

        }
    }

    class EventViewModel: ViewModel(){
        var _img_league  : MutableLiveData<Int> = MutableLiveData()
        var img_league : LiveData<Int> = _img_league

        var _tv_league_name : MutableLiveData<String> = MutableLiveData()
        var tv_league_name : LiveData<String> = _tv_league_name

        fun bind(event : Event){
            _img_league.value = event.idAwayTeam
            _tv_league_name.value = event.homeTeam
        }
    }
}
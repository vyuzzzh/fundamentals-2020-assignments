package com.android.fundamentals.workshop04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.data.models.Actor
import com.android.fundamentals.domain.ActorsDataSource

class WS04DiffUtilsFragment : Fragment() {

    private lateinit var adapter: WS04ActorsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workshop_04, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler: RecyclerView = view.findViewById(R.id.rv_actors)
        adapter = WS04ActorsAdapter()
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        view.findViewById<View>(R.id.shuffle_button).setOnClickListener {
            shuffleActors()
        }
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        adapter.bindActors(ActorsDataSource().getActors())
        adapter.notifyDataSetChanged()
    }

    private fun shuffleActors() {
        val originalList: List<Actor> = ActorsDataSource().getActors()
        val shuffledList: List<Actor> = ActorsDataSource().getActors().shuffled()
        adapter.bindActors(shuffledList)
        // TODO: Replace notifyDataSetChanged for updating the recyclerView to DiffUtil.Callback.
        val diffCallback = ActorsDiffCallback(originalList, shuffledList)
        val diffResult : DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(adapter)
    }

    companion object {
        fun newInstance() = WS04DiffUtilsFragment()
    }
}

class ActorsDiffCallback(private val oldList: List<Actor>, private val newList: List<Actor>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}
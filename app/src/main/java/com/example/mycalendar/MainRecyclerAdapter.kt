package com.example.mycalendar

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mycalendar.database.Schedule
import com.example.mycalendar.databinding.ItemRecyclerviewBinding

class MainRecyclerAdapter(private var scheduleList: List<Schedule>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemRecyclerviewBinding: ItemRecyclerviewBinding

    interface OnItemClickListener {
        fun onItemClick(v:View, position: String, position2: String, position3: String)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener


    inner class ViewHolder(val binding:ItemRecyclerviewBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        itemRecyclerviewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_recyclerview,parent,false)
        return ViewHolder(itemRecyclerviewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.binding.mainTextview.text = scheduleList[position].title
            holder.binding.mainBottomContent.text = scheduleList[position].content
            holder.binding.mainTextview.setOnClickListener {
                itemClickListener.onItemClick(it, position.toString(),scheduleList[position].title, scheduleList[position].content)

            }
        }
    }

    override fun getItemCount(): Int = scheduleList.size

    fun setData(newData: List<Schedule>){
        scheduleList = newData
        notifyDataSetChanged()
    }
}
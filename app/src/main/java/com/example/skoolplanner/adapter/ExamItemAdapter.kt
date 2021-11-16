package com.example.skoolplanner.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skoolplanner.database.Exam
import com.example.skoolplanner.databinding.ExamItemBinding


class ExamItemAdapter(
    private var dataset: MutableList<Exam> = mutableListOf<Exam>()
) : RecyclerView.Adapter<ExamItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val binding: ExamItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val examDate = binding.ExamDate
        val examName = binding.ExamName
        val className = binding.ClassName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ExamItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.examDate.text = item.date
        holder.className.text = item.className
        holder.examName.text = item.name
    }
}
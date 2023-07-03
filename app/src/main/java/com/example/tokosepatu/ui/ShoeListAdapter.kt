package com.example.tokosepatu.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tokosepatu.R
import com.example.tokosepatu.model.Shoe


class ShoeListAdapter(
    private val onItemClickListener: (Shoe) -> Unit
): ListAdapter<Shoe, ShoeListAdapter.ShoeViewHolder>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
       return ShoeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
       val shoe = getItem(position)
        holder.bind(shoe)
        holder.itemView.setOnClickListener {
            onItemClickListener(shoe)
        }
    }

    class ShoeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val  nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val  addressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        private val  ownerTextView: TextView = itemView.findViewById(R.id.ownerTextView)
        fun bind(shoe: Shoe?) {
            nameTextView.text = shoe?.name
            addressTextView.text = shoe?.address
            ownerTextView.text = shoe?.owner


        }

        companion object {
            fun create(parent: ViewGroup): ShoeListAdapter.ShoeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_shoe, parent, false)
                return  ShoeViewHolder(view)


            }
        }

    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Shoe>() {
            override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
               return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


}
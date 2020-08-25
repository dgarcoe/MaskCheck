package com.github.dgarcoe.maskchecker

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ReferenceRecyclerAdapter(private val referenceList: List<Reference>) : RecyclerView.Adapter<ReferenceRecyclerAdapter.ReferenceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReferenceRecyclerAdapter.ReferenceHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item, false)
        return ReferenceHolder(inflatedView)
    }

    override fun getItemCount() = referenceList.size

    override fun onBindViewHolder(holder: ReferenceRecyclerAdapter.ReferenceHolder, position: Int) {
        val itemReference = referenceList[position]
        holder.bindReference(itemReference)
    }

    class ReferenceHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var name: String? = null
        private var url: String? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            view.context.startActivity(intent)
        }

        fun bindReference(reference: Reference) {
            view.itemName.text = reference.name
            url = reference.url
        }

        companion object {
            //5
            private val REFERENCE_KEY = "REFERENCE"
        }
    }

}


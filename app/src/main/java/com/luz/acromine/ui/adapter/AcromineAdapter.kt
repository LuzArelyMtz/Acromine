package com.luz.acromine.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.luz.acromine.api.model.Lf
import com.luz.acromine.databinding.DefinitionItemCardvBinding

class AcromineAdapter : ListAdapter<Lf, AcromineViewHolder>(AcromineDiffCallback()) {
    var term: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcromineViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val definitionItemCardvBinding =
            DefinitionItemCardvBinding.inflate(layoutInflater, parent, false)

        return AcromineViewHolder(definitionItemCardvBinding)
    }

    override fun onBindViewHolder(acromineViewHolder: AcromineViewHolder, position: Int) {
        acromineViewHolder.bindView(getItem(position), term)
    }

}
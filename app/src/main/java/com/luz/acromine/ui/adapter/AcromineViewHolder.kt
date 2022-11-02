package com.luz.acromine.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.luz.acromine.api.model.Lf
import com.luz.acromine.databinding.DefinitionItemCardvBinding

class AcromineViewHolder(private val definitionItemCardvBinding: DefinitionItemCardvBinding) :
    RecyclerView.ViewHolder(definitionItemCardvBinding.root) {
    fun bindView(lf: Lf, term: String) {
        definitionItemCardvBinding.definitionCardvBinding = lf
        definitionItemCardvBinding.acronym = term
    }
}
package com.luz.acromine.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.luz.acromine.api.model.Lf

class AcromineDiffCallback : DiffUtil.ItemCallback<Lf>() {
    override fun areItemsTheSame(oldItem: Lf, newItem: Lf) = oldItem.lf == newItem.lf

    override fun areContentsTheSame(oldItem: Lf, newItem: Lf) = oldItem == newItem
}
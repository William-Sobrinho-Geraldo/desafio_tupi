package com.william.desafio_tupi.adapterRecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.william.desafio_tupi.databinding.ItemRecyclerLogsBinding
import com.william.desafio_tupi.model.Card
import com.william.desafio_tupi.utility.Utility

class AdapterLogs() :
    RecyclerView.Adapter<AdapterLogs.ViewHolder>() {

    private var listaCards: AsyncListDiffer<Card> = AsyncListDiffer(this, DiffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = ItemRecyclerLogsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        Log.i("WillItemCount", "getItemCount: ${listaCards.currentList.size}")
        return listaCards.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < listaCards.currentList.size) {
            holder.bind(listaCards.currentList[position])
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }

    fun updateList(list: List<Card>) {
        listaCards.submitList(list)
    }


    inner class ViewHolder(private val binding: ItemRecyclerLogsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            val txt_nomeTitular = binding.txtCardHolder
            val txt_numeroCartao = binding.txtCardNumber
            val txt_metodoPagamento = binding.txtPaymentMethod
            val txt_codigoSeguranca = binding.txtCvv
            val txt_createdAt = binding.txtCreatedAt


            txt_nomeTitular.text = card.holderName
            txt_numeroCartao.text = card.pan
            txt_metodoPagamento.text = card.cvm
            txt_codigoSeguranca.text = card.cvv

            val dataCriacao = card.createdAt?.let { Utility.formatTimestampToDate(it) }
            txt_createdAt.text = "Registro criado em : $dataCriacao"

        }
    }

}






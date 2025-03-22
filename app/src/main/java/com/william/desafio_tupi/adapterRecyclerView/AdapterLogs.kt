package com.william.desafio_tupi.adapterRecyclerView

//class AdapterLogs {
//}


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.william.desafio_tupi.databinding.ItemRecyclerLogsBinding
import com.william.desafio_tupi.model.Card

class AdapterLogs(
//    val context: Context,
//    private val onItemClick: (ExamUlcer) -> Unit,
) :
    RecyclerView.Adapter<AdapterLogs.ViewHolder>() {

    private var listaCards: AsyncListDiffer<Card> =
        AsyncListDiffer(this, DiffCallBack)


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
        override fun areItemsTheSame(
            oldItem: Card,
            newItem: Card
        ): Boolean {
//            return oldItem.ulcer?.body_parts == newItem.ulcer?.body_parts
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Card,
            newItem: Card
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun updateList(list: List<Card>) {
        listaCards.submitList(list)

//        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemRecyclerLogsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            val txtMensagem = binding.txtMessage    //colocar name piscando se tiver notificação para essa lesão

            val mensagemConcatenada =
                "Titular do cartão: ${card.holderName} \n  Número: ${card.pan} \n  Data de validade: ${card.validDate} \n  Método utilizado: ${card.cvm} \n  Código de segurança: ${card.cvv}"


            txtMensagem.text = mensagemConcatenada
            //colocar aqui a mensagem concatenada


        }
    }

}






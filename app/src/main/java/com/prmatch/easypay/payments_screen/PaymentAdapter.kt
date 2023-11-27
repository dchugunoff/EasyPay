package com.prmatch.easypay.payments_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prmatch.easypay.data.model.payments_entity.Response
import com.prmatch.easypay.databinding.CardPaymentBinding
import java.util.Date

class PaymentAdapter : ListAdapter<Response, PaymentAdapter.PaymentViewHolder>(DiffCallback) {

    inner class PaymentViewHolder(private val binding: CardPaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(payment: Response) {
            with(binding) {
                paymentTitle.text = payment.title
                paymentId.text = payment.id.toString()
                if (payment.created.toLong() > 0) {
                    val date = Date(payment.created.toLong() * 1000L)
                    paymentDate.text = date.toString()
                }
                if (payment.amount != null && payment.amount.isNotBlank()) {
                    paymentAmount.text = payment.amount
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding = CardPaymentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = getItem(position)
        holder.bind(payment)
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Response>() {
            override fun areItemsTheSame(oldItem: Response, newItem: Response): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Response, newItem: Response): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.id == newItem.id
            }

        }
    }

}
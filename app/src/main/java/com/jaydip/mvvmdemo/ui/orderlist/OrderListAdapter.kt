package com.jaydip.mvvmdemo.ui.orderlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jaydip.mvvmdemo.R
import com.jaydip.mvvmdemo.api.response.OrderModel
import com.jaydip.mvvmdemo.databinding.ItemOrderListBinding
import com.jaydip.mvvmdemo.util.AppConstant
import com.jaydip.mvvmdemo.util.parseDateUs
import com.jaydip.mvvmdemo.util.parseOrderDateTime
import kotlinx.android.synthetic.main.item_order_list.view.*


class OrderListAdapter(
    private val listener: OrderItemClickListener,
    private var orderListData: MutableList<OrderModel> = mutableListOf(),
    private val isCompletedRecord: Boolean
) : RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {
    private var orderList: MutableList<OrderModel> = orderListData


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_order_list, parent, false)
        )
    }

    @SuppressLint("StringFormatMatches")
    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderDetails = orderList[position]
        if (isCompletedRecord) {
            holder.itemView.itemOrderListIvPickupLocation.setImageResource(R.drawable.ic_location_source)
            holder.itemView.itemOrderListIvTime.setImageResource(R.drawable.ic_date)
        } else {
            holder.itemView.itemOrderListIvPickupLocation.setImageResource(R.drawable.ic_direction)
            holder.itemView.itemOrderListIvTime.setImageResource(R.drawable.ic_time)
        }
        orderDetails.let {
            holder.itemView.itemOrderListTvName.text = it.from_addr_addr_name
            holder.itemView.itemOrderListTvOrderNo.text =
                holder.itemView.itemOrderListTvOrderNo.context.getString(
                    R.string.str_order_no,
                    it.ord_id
                )
            var fromAddress: String? = ""

            if (it.from_addr_addr_1.isNotEmpty()) {
                fromAddress = fromAddress + it.from_addr_addr_1 + ", "
            }
            if (it.from_addr_addr_2.isNotEmpty()) {
                fromAddress = fromAddress + it.from_addr_addr_2 + ", "
            }
            if (it.from_addr_area.isNotEmpty()) {
                fromAddress = fromAddress + it.from_addr_area + ", "
            }
            if (it.from_addr_city.isNotEmpty()) {
                fromAddress = fromAddress + it.from_addr_city + ", "
            }
            if (it.from_addr_state.isNotEmpty()) {
                fromAddress = fromAddress + it.from_addr_state + ", "
            }
            if (it.from_addr_pincode.isNotEmpty()) {
                fromAddress = fromAddress + it.from_addr_pincode + ", "
            }
            if (fromAddress!!.isNotEmpty()) {
                if (fromAddress.endsWith(", ")) {
                    fromAddress =
                        fromAddress.substring(0, fromAddress.length - 2)
                }
                holder.itemView.itemOrderListTvPickupLocation.text = fromAddress
            } else {
                holder.itemView.itemOrderListTvPickupLocation.text = ""
            }
            if (isCompletedRecord) {
                var toAddress: String? = ""

                if (it.to_addr_addr_1.isNotEmpty()) {
                    toAddress = toAddress + it.to_addr_addr_1 + ", "
                }
                if (it.to_addr_addr_2.isNotEmpty()) {
                    toAddress = toAddress + it.to_addr_addr_2 + ", "
                }
                if (it.to_addr_area.isNotEmpty()) {
                    toAddress = toAddress + it.to_addr_area + ", "
                }
                if (it.to_addr_city.isNotEmpty()) {
                    toAddress = toAddress + it.to_addr_city + ", "
                }
                if (it.to_addr_state.isNotEmpty()) {
                    toAddress = toAddress + it.to_addr_state + ", "
                }
                if (it.to_addr_pincode.isNotEmpty()) {
                    toAddress = toAddress + it.to_addr_pincode + ", "
                }
                if (toAddress!!.isNotEmpty()) {
                    if (toAddress.endsWith(", ")) {
                        toAddress =
                            toAddress.substring(0, toAddress.length - 2)
                    }
                    holder.itemView.itemOrderListTvDropLocation.text = toAddress
                } else {
                    holder.itemView.itemOrderListTvDropLocation.text = ""
                }
            }

            holder.itemView.itemOrderListTvWeight.text =
                holder.itemView.itemOrderListTvWeight.context.getString(
                    R.string.str_order_weight,
                    it.weight
                )
            holder.itemView.itemOrderListTvPrice.text = it.amount.toString()
            if (isCompletedRecord) {
                holder.itemView.itemOrderListTvTime.text = it.pick_dt.parseDateUs(
                    format = AppConstant.DATE_FORMAT_SERVER,
                    convertFormat = AppConstant.BOOKING_DATE_DMY_FORMAT_DISPLAY
                )
            } else {
                holder.itemView.itemOrderListTvTime.text = it.pick_dt.parseOrderDateTime(
                    format = AppConstant.DATE_FORMAT_SERVER,
                    convertFormat = AppConstant.DATE_FORMAT_DISPLAY
                )
            }
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(orderDetails, position)
        }

        if (position == orderList.size - 1) {
            holder.bind(orderDetails, true)
        } else {
            holder.bind(orderDetails, false)
        }

    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var mListBinding: ItemOrderListBinding? = null

        init {
            mListBinding = DataBindingUtil.bind(itemView)
        }

        fun bind(orderModel: OrderModel, isLastRecord: Boolean) {
            mListBinding!!.model = orderModel
            mListBinding!!.isCompletedRecord = isCompletedRecord
            mListBinding!!.isLastRecord = isLastRecord
        }
    }

    interface OrderItemClickListener {
        fun onItemClick(orderModel: OrderModel, position: Int)
    }

}
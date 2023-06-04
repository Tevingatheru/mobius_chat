package com.millenial.mobiuschat

import android.R
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


private class SentMessageHolder internal constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    var messageText: TextView
    var timeText: TextView

    init {
        messageText = itemView.findViewById<View>(R.id.text_gchat_message_me) as TextView
        timeText = itemView.findViewById<View>(R.id.text_gchat_timestamp_me) as TextView
    }

    fun bind(message: UserMessage) {
        messageText.setText(message.getMessage())

        // Format the stored timestamp into a readable String using method.
        timeText.setText(Utils.formatDateTime(message.getCreatedAt()))
    }
}
package com.millenial.mobiuschat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MessageAdapter(messageList: List<Message>, context: Context) :
    RecyclerView.Adapter<ViewHolder?>() {
    private val VIEW_TYPE_MESSAGE_SENT = 1
    private val VIEW_TYPE_MESSAGE_RECEIVED = 2

    private val mMessageList: List<Message>
    private val mContext: Context

    init {
        mMessageList = messageList
        mContext = context
    }

    override fun getItemCount(): Int {
        return mMessageList.size
    }

    // Determines the appropriate ViewType according to the sender of the message.
    override fun getItemViewType(position: Int): Int {
        val message: Message = mMessageList[position]

        return if (message.sender.id?.equals(Mobius.getCurrentUser().id) == true) {
            // If the current user is the sender of the message
            VIEW_TYPE_MESSAGE_SENT
        } else {
            // If some other user sent the message
            VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.send_message, parent, false)
            return SentMessageHolder(view)
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recieve_message, parent, false)
            return ReceivedMessageHolder(view, mContext)
        }
        view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recieve_message, parent, false)
        return ReceivedMessageHolder(view, mContext)
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message: Message = mMessageList[position] as Message
        when (holder.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT -> (holder as SentMessageHolder).bind(message)
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as ReceivedMessageHolder).bind(message)
        }
    }

    private class SentMessageHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var messageText: TextView
        var timeText: TextView

        init {
            messageText = itemView.findViewById<View>(R.id.text_gchat_message_me) as TextView
            timeText = itemView.findViewById<View>(R.id.text_gchat_timestamp_me) as TextView
        }

        fun bind(message: Message) {
            messageText.setText(message.message)

            // Format the stored timestamp into a readable String using method.
            timeText.setText(Utils.formatDateTime(message.createdTime))
        }
    }

    private class ReceivedMessageHolder internal constructor(itemView: View, val mContext: Context) :
        RecyclerView.ViewHolder(itemView) {
        var messageText: TextView
        var timeText: TextView
        var nameText: TextView
        var profileImage: ImageView

        init {
            messageText = itemView.findViewById<View>(R.id.text_gchat_user_other) as TextView
            timeText = itemView.findViewById<View>(R.id.text_gchat_timestamp_other) as TextView
            nameText = itemView.findViewById<View>(R.id.text_gchat_message_other) as TextView
            profileImage = itemView.findViewById<View>(R.id.image_gchat_profile_other) as ImageView
        }

        fun bind(message: Message) {
            messageText.setText(message.message)

            // Format the stored timestamp into a readable String using method.
            timeText.setText(Utils.formatDateTime(message.createdTime))
            nameText.setText(message.sender!!.nickname)

            // Insert the profile image from the URL into the ImageView.

            Utils.displayRoundImageFromUrl(
                mContext,
                message.sender!!.profileUrl,
                profileImage
            )
        }
    }
}

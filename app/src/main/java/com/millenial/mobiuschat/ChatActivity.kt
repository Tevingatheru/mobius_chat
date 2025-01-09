package com.millenial.mobiuschat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking

class ChatActivity : AppCompatActivity() {
    private var mMessageRecycler: RecyclerView? = null
    private var messageList: MutableList<Message> = mutableListOf()
    private var mMessageAdapter: MessageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMessageRecycler = findViewById<RecyclerView>(R.id.recycler_chat)
        mMessageAdapter = MessageAdapter(messageList, this)

        mMessageRecycler!!.setLayoutManager(LinearLayoutManager(this))
        mMessageRecycler!!.setAdapter(mMessageAdapter)

        val button = findViewById<Button>(R.id.button_gchat_send)
        button.setOnClickListener {
            Log.d("Button Click", "Clicked")
            val sendMessageHolder = findViewById<TextView>(R.id.edit_gchat_message)
            val newMessage = Message(sendMessageHolder?.text.toString(), Mobius.getCurrentUser(), System.currentTimeMillis())

            val aiHandler: AiHandler  =  AiHandler()
            runBlocking { aiHandler.prompt(newMessage.message) }

            messageList.add(newMessage)
            mMessageAdapter?.notifyItemInserted(messageList.size - 1)
        }
    }
}

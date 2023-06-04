package com.millenial.mobiuschat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ChatActivity : AppCompatActivity() {
    private var mMessageRecycler: RecyclerView? = null
    private var mMessageAdapter: MessageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        mMessageRecycler = findViewById<View>(R.id.recycler_gchat) as RecyclerView
        mMessageAdapter = MessageAdapter(this, messageList)
        mMessageRecycler.setLayoutManager(LinearLayoutManager(this))
        mMessageRecycler.setAdapter(mMessageAdapter)
    }
}

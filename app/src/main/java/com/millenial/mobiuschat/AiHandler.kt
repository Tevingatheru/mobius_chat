package com.millenial.mobiuschat

import android.util.Log
import com.millenial.mobiuschat.RetrofitClient.Companion.getRetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.net.SocketException

class AiHandler {
    private val aiService: AiService = getRetrofitInstance()
        .create(AiService::class.java)

    suspend fun prompt(message: String): String {
        try {
            return withContext(Dispatchers.IO) {
                Log.i(TAG, "Start prompting")
                val responseCall: Call<PromptDto> = aiService.prompt(PromptDto(message))
                val body : PromptDto? = responseCall
                    .execute()
                    .body()

                Log.i(TAG, "End prompting: $body")
                body!!.message
            }
        } catch(e: SocketException) {
            Log.e(TAG, "SocketException making prompt: ${e.message}")
            return ""
        } catch (e: Exception) {
            Log.e(TAG, "Error making prompt: $e")
            return ""
        }
    }

    companion object {
        private const val TAG = "AiHandler"
    }
}

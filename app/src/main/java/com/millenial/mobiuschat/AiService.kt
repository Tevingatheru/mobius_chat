package com.millenial.mobiuschat

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AiService {
    @POST("prompt")
    fun prompt(@Body message: PromptDto) : Call<PromptDto>
}

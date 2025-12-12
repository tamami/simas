package lab.aikibo.simasganteng.config

import lab.aikibo.simasganteng.tools.CustomHttpClient
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class OkHttpConfig {

    @Bean
    fun okHttpClient(): OkHttpClient {
        return CustomHttpClient.createUnsafeOkHttpClient()
            .newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

}
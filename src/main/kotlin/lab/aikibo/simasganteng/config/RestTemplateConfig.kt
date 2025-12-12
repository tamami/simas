package lab.aikibo.simasganteng.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory

//import org.apache.http.impl.client.HttpClientBuilder
//import org.apache.http.conn.ssl.NoopHostnameVerifier
//import org.apache.http.ssl.SSLContexts


@Configuration
class RestTemplateConfig {

//    @Bean fun restTemplate(): RestTemplate {
//        val httpClient = HttpClientBuilder.create()
//            .setSSLContext(SSLContexts.custom().loadTrustMaterial { _, _ -> true }.build())
//            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
//            .build()
//
//        val factory = HttpComponentsClientHttpRequestFactory(httpClient as HttpClient)
//        return RestTemplate(factory)
//    }
@Bean fun restTemplate(): RestTemplate = RestTemplate()

}
package lab.aikibo.simasganteng.tools

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class Base64Converter {

    companion object {
        @OptIn(ExperimentalEncodingApi::class)
        fun encodeBase64Double(input: String): String {
            val first = Base64.encode(input.toByteArray())
            val second = Base64.encode(first.toByteArray())
            return second
        }

        fun encodeIdKegiatanSimasganteng(input: String): String {
            val teks = "bre#198404092010011025-" + input + "#best"
            return encodeBase64Double(teks)
        }

        @OptIn(ExperimentalEncodingApi::class)
        fun decodedBase64Double(input: String): String {
            val first = Base64.decode(input.toByteArray())
            val second = Base64.decode(first)
            return second.toString()
        }
    }

}
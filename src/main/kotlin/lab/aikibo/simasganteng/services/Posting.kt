package lab.aikibo.simasganteng.services

import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Service
class Posting {

    val restTemplate = RestTemplate()
    val log = LoggerFactory.getLogger(Posting::class.java)
    val url = "https://simasganteng-app4.brebeskab.go.id/sitampan/kin-harian/create?tgl=WW5KbEl6SXdNalV0T0MweE5pTmlaWE4w"
    val cookie = "bkpsdmd-brebeskab-app=p1m8dfenokj052qfjj4si1gfa4"
    val csrfApp = "L_W_ukwiQz2av-NY-qay8Ap3jpHdl7iBLUKg7ioCAxJ3w_D5C1cAdc7ckiqfkYqgYAbD1qXa-9lVEuqfU1VoKg=="

    fun pushOne(cookie: String, csrfApp: String, tgl: LocalDate, uraian: Uraian): ResponseEntity<String> {
        val isoFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val indoFormat = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("id", "ID"))
        val longFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        // proses header
        log.info("siapkan header")
        val headers = HttpHeaders().apply {
            add("Cookie", cookie)
        }
        log.info("header : $headers")

        // prepare body
        val formData = LinkedMultiValueMap<String, String>()
        formData.add("_csrf-app", csrfApp)
        formData.add("tgl-kinharian-tgl-disp", tgl.format(indoFormat))
        formData.add("KinHarian[tgl]", tgl.format(isoFormat))
        formData.add("kinharian-target_kuan_h-disp", "1")
        formData.add("KinHarian[target_kuan_h]", "1")
        formData.add("KinHarian[target_output_h]", "Kegiatan")
        formData.add("twaktu-disp", uraian.durasi.toString())
        formData.add("KinHarian[target_waktu_h]", "${uraian.durasi}")
        formData.add("KinHarian[tgl_target]", LocalDateTime.now().format(longFormat))
        formData.add("KinHarian[uraian_keg_h]", uraian.uraian)

        val reqEntity = HttpEntity(formData, headers)
        val response = restTemplate.postForEntity(url, reqEntity, String::class.java)
        log.info(" >> ${uraian.uraian} >> ${response.statusCode}")
        return response
    }

    fun push(tglPanjang: String, tgl: String, tglPosting: String, uraian: List<Uraian>) {
        // prepare header
        log.info("siapkan header")
        val headers = HttpHeaders().apply {
            add("Cookie", cookie)
        }
        log.info("header : $headers")

        // prepare body
        uraian.forEach {
            val formData = LinkedMultiValueMap<String, String>()
            formData.add("_csrf-app", csrfApp)
            formData.add("tgl-kinharian-tgl-disp", tglPanjang)
            formData.add("KinHarian[tgl]", tgl)
            formData.add("kinharian-target_kuan_h-disp", "1")
            formData.add("KinHarian[target_kuan_h]", "1")
            formData.add("KinHarian[target_output_h]", "Kegiatan")
            formData.add("twaktu-disp", it.durasi.toString())
            formData.add("KinHarian[target_waktu_h]", "${it.durasi}")
            formData.add("KinHarian[tgl_target]", tglPosting)
            formData.add("KinHarian[uraian_keg_h]", it.uraian)

            val reqEntity = HttpEntity(formData, headers)
            val response = restTemplate.postForEntity(url, reqEntity, String::class.java)
            log.info(" >> ${it.uraian} >> ${response.statusCode}")
            Thread.sleep(1_500)
        }
    }

    fun batchPush(cookie: String, csrfApp: String, daftarTgl: List<LocalDate>, uraian: List<Uraian>) {
//        val isoFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//        val indoFormat = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("id", "ID"))
//        val longFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        daftarTgl.forEach {
//            log.info("iso formatter : " + it.format(isoFormat))
//            log.info("indo format : " + it.format(indoFormat))
//            log.info("long format : " + it.format(longFormat))
            uraian.forEach { el: Uraian ->
                pushOne(cookie, csrfApp, it, el)
                Thread.sleep(1_000)
            }
        }
    }

}

data class Uraian(
    var uraian: String,
    var durasi: Int
) {
    constructor(): this("", 0)

    override fun toString(): String {
        return "[ $uraian : $durasi ]"
    }
}
package lab.aikibo.simasganteng.services

import lab.aikibo.simasganteng.model.Realisasi
import lab.aikibo.simasganteng.tools.Base64Converter
import lab.aikibo.simasganteng.tools.CustomHttpClient
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class PostingOkHttp {

    val urlRealisasi = "https://simasganteng-app4.brebeskab.go.id/sitampan/kin-harian/update-rel?id="
    val urlPenilaian = "https://simasganteng-app.brebeskab.go.id/sitampan/kin-harian/update-nil?id="
    val log = LoggerFactory.getLogger(PostingOkHttp::class.java)
    private val http: OkHttpClient by lazy {
        CustomHttpClient.createUnsafeOkHttpClient()
            .newBuilder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .build()
    }


    fun pushRealisasi(cookie: String, csrfApp: String,
                      realisasiWaktu: String, // format: YYYY-MM-DD)
                      id: String, waktuPengerjaan: String,
                      realisasiWaktuDetail: String // format: YYYY-MM-DD HH:MM:SS
    ): String {
        log.info("membentuk client http...")
        val client = http
        val idBase64 = Base64Converter.encodeIdKegiatanSimasganteng(id)
        log.info("membantuk id base 64 : $idBase64")
        val formBody = FormBody.Builder()
            .add("_csrf-app", csrfApp)
            .add("KinHarian[tgl]", realisasiWaktu)
            .add("kinharian-real_kuan_h-disp", "1")
            .add("KinHarian[real_kuan_h]", "1")
            .add("trealwaktu-disp", waktuPengerjaan)
            .add("KinHarian[real_waktu_h]", waktuPengerjaan)
            .add("KinHarian[real_output_h]", "Kegiatan")
            .add("KinHarian[tgl_real]", realisasiWaktuDetail)
            .build()
        log.info("berhasil membentuk body request")
        val request = Request.Builder()
            .url(urlRealisasi + idBase64)
            .header("Cookie", "bkpsdmd-brebeskab-app=$cookie")
            .post(formBody)
            .build()
        log.info("berhasil membentuk request keseluruhan...")

        log.info("melakukan request ...")
        client.newCall(request).execute().use { response ->
            val body = response.body?.string() ?: ""
            //log.info("pushRealisasi response: $body")
            if(response.isSuccessful) log.info("proses $id berhasil")
            else log.info("proses $id selesai")
            return body
        }
    }

    fun batchPushRealisasi(cookie: String, csrfApp: String, data: List<Realisasi>) {
        for(it in data) {
            log.info("proses : ${it.id} - ${it.realisasiWaktu}")
            pushRealisasi(cookie, csrfApp, it.realisasiWaktu, it.id, it.waktuPengerjaan,
                it.realisasiWaktuDetail)
            Thread.sleep(1_000)
        }
    }

    // TODO: penilaian
    // url : https://simasganteng-app.brebeskab.go.id/sitampan/kin-harian/update-nil?
    //         id=WW5KbEl6RTVPRFF3TkRBNU1qQXhNREF4TVRBeU5TMHhOelkwTlRVM05qVTBJMkpsYzNRPQ==
    // body :
    // _csrf-app:
    //  xu_ofbU2UMHIJWoKTUcvc8kAyAHTz0xN5OND6xWBnmatrd1N8kQ_u5cVGT0kJUUxjVKpObyWDivVuha4W8TqKQ==
    //KinHarian[tgl]: '2025-12-19'
    //KinHarian[penilaian]: ''
    //KinHarian[ok_kuan_h]: '1'
    //KinHarian[ok_waktu_h]: '60'
    //KinHarian[ok_output_h]: Kegiatan
    //KinHarian[tgl_ok]: '2025-12-11 20:07:07'
    //submit:
    //- setuju
    //- setuju
    fun pushPenilaian(cookie: String, csrfApp: String, id: String,
                      tglKegiatan: String,   // format: YYYY-MM-DD
                      lamaKegiatan: String,
                      tglPenilaian: String // format: YYYY-MM-DD hh:mm:ss
    ): String {
        log.info("membentuk client http ...")
        val client = http
        val idBase64 = Base64Converter.encodeIdKegiatanSimasganteng(id)
        log.info("membentuk id base 64 : $idBase64")
        val formBody = FormBody.Builder()
            .add("_csrf-app", csrfApp)
            .add("KinHarian[tgl]", tglKegiatan)
            .add("KinHarian[penilaian]", "")
            .add("KinHarian[ok_kuan_h]", "1")
            .add("KinHarian[ok_waktu_h]", lamaKegiatan)
            .add("KinHarian[ok_output_h]", "Kegiatan")
            .add("KinHarian[tgl_ok]", tglPenilaian)
            .add("submit[]", "setuju")
            .add("submit[]", "setuju")
            .build()
        log.info("berhasil membentuk body request")
        val req = Request.Builder()
            .url(urlPenilaian + idBase64)
            .header("Cookie", "bkpsdmd-brebeskab-app=$cookie")
            .post(formBody)
            .build()
        log.info("berhasil membentuk request keseluruhan ...")

        log.info("melakukan request ...")
        client.newCall(req).execute().use { resp ->
            val body = resp.body?.string() ?: ""
            if(resp.isSuccessful) log.info("proses $id berhasil")
            else log.info("proses $id selesai")
            return body
        }
    }
}
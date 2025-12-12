package lab.aikibo.simasganteng.services

import lab.aikibo.simasganteng.model.Realisasi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostingOkHttpTest {

    val log = LoggerFactory.getLogger(PostingOkHttpTest::class.java)
    @Autowired lateinit var postingOkHttp: PostingOkHttp

    @Test fun pushRealisasiTest() {
        val result = postingOkHttp.pushRealisasi(
            "t30978sr4m3ecekqth8t9qf569",
            "wldpPCMTcOfcO_N-E1QP1RwVyJFz9WDyPQ6yXmnPRXCJOx1-cFsz1YZDtkZ2AVuCeFeB3zyjGrlXRMBuLoosMQ==",
            "2025-12-18",
            "1764557651", "270",
            "2025-12-18 15:41:00"
        )
        log.info("result : $result")
        Assertions.assertTrue(true)
    }

    @Test fun testOnlyOkHttp() {
        val client = okhttp3.OkHttpClient()
        Assertions.assertTrue(true)
    }

    @Test fun batchPushRealisasiTest() {
        val cookie = "ouilu49r1dim1e8pn3fp78klcd"
        val csrfApp = "Dpdi5aliZyzTxrjUVX9oMBBOHxWi1A_c0KN3-POuTXh59jexz1IWaIue3-UjDRJzei9tLfviao397zqIoJsAIA=="
        val data = listOf(
//            Realisasi("1765466925", "60", "2025-12-31", "2025-12-31 15:24:00"),
//            Realisasi("1765466926", "60", "2025-12-31", "2025-12-31 15:24:00"),
//            Realisasi("1765466927", "270", "2025-12-31", "2025-12-31 15:24:00"),
            Realisasi("1765505440", "60", "2026-1-2", "2026-1-2 15:24:00"),
            Realisasi("1765505443", "60", "2026-1-2", "2026-1-2 15:24:00"),
            Realisasi("1765505444", "270", "2026-1-2", "2026-1-2 15:24:00")
//            Realisasi("1764557721", "60", "2025-12-20", "2025-12-20 15:25:00"),
//            Realisasi("1764557728", "60", "2025-12-20", "2025-12-20 15:25:00"),
//            Realisasi("1764557733", "270", "2025-12-20", "2025-12-20 15:25:00"),
//            Realisasi("1764557669", "60", "2025-12-22", "2025-12-22 15:26:00"),
//            Realisasi("1764557670", "60", "2025-12-22", "2025-12-22 15:26:00"),
//            Realisasi("1764557671", "270", "2025-12-22", "2025-12-22 15:26:00"),
//            Realisasi("1764557675", "60", "2025-12-23", "2025-12-23 15:27:00"),
//            Realisasi("1764557683", "60", "2025-12-23", "2025-12-23 15:27:00"),
//            Realisasi("1764557686", "270", "2025-12-23", "2025-12-23 15:27:00"),
//            Realisasi("1764557694", "60", "2025-12-26", "2025-12-26 15:28:00"),
//            Realisasi("1764557695", "60", "2025-12-26", "2025-12-26 15:28:00"),
//            Realisasi("1764557696", "270", "2025-12-26", "2025-12-26 15:28:00"),
//            Realisasi("1764557735", "60", "2025-12-27", "2025-12-27 15:29:00"),
//            Realisasi("1764557736", "60", "2025-12-27", "2025-12-27 15:29:00"),
//            Realisasi("1764557737", "270", "2025-12-27", "2025-12-27 15:29:00"),
//            Realisasi("1764557709", "60", "2025-12-29", "2025-12-29 15:30:00"),
//            Realisasi("1764557710", "60", "2025-12-29", "2025-12-29 15:30:00"),
//            Realisasi("1764557711", "270", "2025-12-29", "2025-12-29 15:30:00"),
//            Realisasi("1764557713", "60", "2025-12-30", "2025-12-30 15:31:00"),
//            Realisasi("1764557714", "60", "2025-12-30", "2025-12-30 15:31:00"),
//            Realisasi("1764557715", "270", "2025-12-30", "2025-12-30 15:31:00")
        )
        postingOkHttp.batchPushRealisasi(cookie, csrfApp, data)
        Assertions.assertTrue(true)
    }

    @Test fun pushPenilaianTest() {
        val result = postingOkHttp.pushPenilaian("0niston7ft3ifr88birn4s13ji",
            "gcMJcGFFKZO20O_LjmJ2QPN6iGCs66r71DO6yfgQOIXQ80g5LyllwI6ahv2_ICk1mBO9VePG2s-ZQ_yizlFOvA==",
            "1764557641", "2025-12-18", "60", "2025-12-11 22:08:00")
        log.info("result : $result")
        Assertions.assertTrue(true)
    }

}
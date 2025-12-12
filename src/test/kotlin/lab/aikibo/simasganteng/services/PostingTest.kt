package lab.aikibo.simasganteng.services

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

@SpringBootTest
class PostingTest {

    @Autowired lateinit var posting: Posting
    val log = LoggerFactory.getLogger(PostingTest::class.java)
    @Autowired
    lateinit var restTemplate: RestTemplate



    @Test
    fun pushTest() {
        val data = listOf(
            Uraian("penderasan pbb", 480)
            //Uraian("rekon data pembayaran", 60),
            //Uraian("pengembangan aplikasi", 270)
        )
        posting.push("19 September 2025", "2025-09-19", "2025-09-19 15:32:22", data)
        Assertions.assertTrue(true)
    }

    @Test
    fun batchPushTest() {
        val data = listOf(
//            LocalDate.of(2025, 12, 31),
            LocalDate.of(2026, 1, 2),
            LocalDate.of(2026, 1, 3),
            LocalDate.of(2026, 1, 5),
            LocalDate.of(2026, 1, 6),
            LocalDate.of(2026, 1, 7),
            LocalDate.of(2026, 1, 8),
            LocalDate.of(2026, 1, 9),
            LocalDate.of(2026, 1, 12),
            LocalDate.of(2026, 1, 13),
            LocalDate.of(2026, 1, 14),
            LocalDate.of(2026, 1, 15),
            LocalDate.of(2026, 1, 16),
            LocalDate.of(2026, 1, 19),
            LocalDate.of(2026, 1, 20),
            LocalDate.of(2026, 1, 21),
            LocalDate.of(2026, 1, 22),
            LocalDate.of(2026, 1, 23),
            LocalDate.of(2026, 1, 26),
            LocalDate.of(2026, 1, 27),
            LocalDate.of(2026, 1, 28),
            LocalDate.of(2026, 1, 29),
            LocalDate.of(2026, 1, 30)
//            LocalDate.of(2026, 1, 27)
//            LocalDate.of(2025, 10, 29),
//            LocalDate.of(2025, 10, 30),
//            LocalDate.of(2025, 10, 31)
        )
        posting.batchPush(
            "bkpsdmd-brebeskab-app=ouilu49r1dim1e8pn3fp78klcd",
            "xLA4rkDqvKdoL8dJ7j66-A7nSgO6LEwhtbr80ufJDk2z0W36JtrN4zB3oHiYTMC7ZIY4O-MaKXCY9rGitPxDFQ==",
            data,
            listOf(
                Uraian("backup db", 60),
                Uraian("rekon data pembayaran", 60),
                Uraian("pengembangan aplikasi", 270)
            ))
        Assertions.assertTrue(true)
    }

    @Test fun pushRealisasiTest() {
        val result = posting.pushRealisasi(
            "bkpsdmd-brebeskab-app=ccbfllkagmjmc13bc23ursa6au",
            "0bMXHKvJ2Q3-HMBZVdIjcKaIXuODbGBOqTfucW7Wh-Go43tf46KuNbVThhMXhnAT4r0zgekYIz_kZYMuNJ7ppw==",
            "2025-12-17",
            "1764557639", "60",
            "2025-12-17 13:05:00"
        )
        Assertions.assertTrue(true)
    }

}
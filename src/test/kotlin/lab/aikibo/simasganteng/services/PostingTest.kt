package lab.aikibo.simasganteng.services

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class PostingTest {

    @Autowired lateinit var posting: Posting
    val log = LoggerFactory.getLogger(PostingTest::class.java)

    @Test
    fun pushTest() {
        val data = listOf(
            Uraian("backup data", 60),
            Uraian("rekon data pembayaran", 60),
            Uraian("pengembangan aplikasi", 270)
        )
        posting.push("9 September 2025", "2025-09-09", "2025-09-01 15:32:22", data)
        Assertions.assertTrue(true)
    }

    @Test
    fun batchPushTest() {
        val data = listOf(
            LocalDate.of(2025, 9, 10),
            LocalDate.of(2025, 9, 11),
            LocalDate.of(2025, 9, 12),
            LocalDate.of(2025, 9, 15),
            LocalDate.of(2025, 9, 16),
            LocalDate.of(2025, 9, 17),
            LocalDate.of(2025, 9, 18),
            LocalDate.of(2025, 9, 19),
            LocalDate.of(2025, 9, 22),
            LocalDate.of(2025, 9, 23),
            LocalDate.of(2025, 9, 24),
            LocalDate.of(2025, 9, 25),
            LocalDate.of(2025, 9, 26),
            LocalDate.of(2025, 9, 29),
            LocalDate.of(2025, 9, 30)
        )
        posting.batchPush(
            "bkpsdmd-brebeskab-app=ksh79g2dp4noqdcknc4iruq9v2",
            "YerBRWXHxrOTlZJiY1jvkwuS8akitHZudqxgVK4F_x0LgpQiXK6Fgszf8DIob9f4T9i55xbuDCg0ny0u1zy6KQ==",
            data,
            listOf(
                Uraian("backup data", 60),
                Uraian("rekon data pembayaran", 60),
                Uraian("pengembangan aplikasi", 270)
            ))
        Assertions.assertTrue(true)
    }

}
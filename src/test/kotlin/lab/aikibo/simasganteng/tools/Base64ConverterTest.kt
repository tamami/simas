package lab.aikibo.simasganteng.tools

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Base64ConverterTest {

    val log = LoggerFactory.getLogger(Base64ConverterTest::class.java)

    @Test fun encodeBase64DoubleTest() {
        val result = Base64Converter.encodeBase64Double("bre#198404092010011025-1764557641#best")
        log.info("result : $result")
        Assertions.assertTrue(true)
    }

    @Test fun printTest() {
        log.info("test cetak")
        Assertions.assertTrue(true)
    }

}
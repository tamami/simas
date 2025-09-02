package lab.aikibo.simasganteng.model

import lab.aikibo.simasganteng.services.Uraian
import java.time.LocalDate

data class ReqParam(
    var cookie: String,
    var csrfApp: String,
    var tgl: LocalDate,
    var uraian: Uraian
) {
    constructor(): this("", "", LocalDate.now(), Uraian())

    override fun toString(): String {
        return "[ $cookie : $csrfApp : $tgl : $uraian ]"
    }
}

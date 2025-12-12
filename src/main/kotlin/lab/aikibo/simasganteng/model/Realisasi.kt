package lab.aikibo.simasganteng.model

data class Realisasi(
    var id: String,
    var waktuPengerjaan: String,
    var realisasiWaktu: String, // format: YYYY-MM-DD
    var realisasiWaktuDetail: String // format: YYYY-MM-DD HH:MM:SS
) {
    constructor(): this("", "", "", "")
    override fun toString(): String {
        return "[ $id : $waktuPengerjaan : $realisasiWaktu : $realisasiWaktuDetail ]"
    }
}

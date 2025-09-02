package lab.aikibo.simasganteng.controller

import lab.aikibo.simasganteng.model.ReqParam
import lab.aikibo.simasganteng.services.Posting
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/simas/api")
class ApiController {

    @Autowired lateinit var service: Posting

    val log = LoggerFactory.getLogger(ApiController::class.java)

    @RequestMapping(value = [ "/push" ], method = [ RequestMethod.POST ])
    fun push(@RequestBody param: ReqParam): ResponseEntity<String> {
        log.info("/simas/api/push with body $param")
        log.info("push($param)")
        return service.pushOne(param.cookie, param.csrfApp, param.tgl, param.uraian)
    }

}
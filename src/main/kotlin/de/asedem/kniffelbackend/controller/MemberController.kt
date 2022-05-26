package de.asedem.kniffelbackend.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping (path = ["kniffel/members"])
class MemberController {

    @PostMapping (path = ["join/{member}/{join}"])
    fun joinGame() {

        TODO("Hier muss noch der aufruf in den Service stattfinden")
    }
}
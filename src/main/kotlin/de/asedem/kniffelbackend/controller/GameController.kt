package de.asedem.kniffelbackend.controller

import de.asedem.kniffelbackend.model.ReceiveRequest
import de.asedem.kniffelbackend.model.SendRequest
import de.asedem.kniffelbackend.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping (path = ["kniffel"])
class GameController(private val gameService: GameService) {

    @GetMapping
    fun getDiceRolling(@RequestBody receiveRequest: ReceiveRequest): ResponseEntity<SendRequest> {
        return ResponseEntity.ok(gameService.calculateDices(receiveRequest))
    }
}
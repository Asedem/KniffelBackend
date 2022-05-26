package de.asedem.kniffelbackend.controller

import de.asedem.kniffelbackend.model.ThrowDTO
import de.asedem.kniffelbackend.model.ThrowAnswerDTO
import de.asedem.kniffelbackend.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping (path = ["kniffel/game"])
class GameController(private val gameService: GameService) {

    @GetMapping
    fun getDiceRolling(@RequestBody throwDTO: ThrowDTO): ResponseEntity<ThrowAnswerDTO> {
        return ResponseEntity.ok(gameService.calculateDices(throwDTO))
    }
}
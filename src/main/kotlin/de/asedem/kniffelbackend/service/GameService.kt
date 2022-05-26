package de.asedem.kniffelbackend.service

import de.asedem.kniffelbackend.model.ThrowDTO
import de.asedem.kniffelbackend.model.ThrowAnswerDTO
import org.springframework.stereotype.Service

@Service
class GameService() {

    val memberService: MemberService = MemberService()

    /**
     * Calculates the Dice results given in the [throwDTO] to an output,
     * that can be sent via HttpRequests to the Client
     * @return the SendRequest that will be returned to the Client
     */
    fun calculateDices(throwDTO: ThrowDTO): ThrowAnswerDTO {

        // Checking if the given game exists in the Cache
        if (memberService.getMembersOfGameIfPresent(throwDTO.member) == null)
            return ThrowAnswerDTO(true, "Deine GameID ist nicht in der Datenbank enthalten!", 0)

        // Checking if the given member is contained in the game
        val currentGameMembers: Array<Int> = memberService.getMembersOfGameIfPresent(throwDTO.game)!!
        if (!currentGameMembers.contains(throwDTO.member))
            return ThrowAnswerDTO(true, "Deine MemberID ist nicht deinem Spiel zugehörig", 0)

        // Calculating the points Thrown in the roll
        var points = 0;
        throwDTO
            .toIntArray()
            .forEach { points += it }

        // Returning the Result
        return ThrowAnswerDTO(false, "Punktzahl: ", points)
    }


}
package de.asedem.kniffelbackend.service

import de.asedem.kniffelbackend.model.ReceiveRequest
import de.asedem.kniffelbackend.model.SendRequest
import org.springframework.stereotype.Service

@Service
class GameService() {

    val memberService: MemberService = MemberService()

    /**
     * Calculates the Dice results given in the [receiveRequest] to an output,
     * that can be sent via HttpRequests to the Client
     * @return the SendRequest that will be returned to the Client
     */
    fun calculateDices(receiveRequest: ReceiveRequest): SendRequest {

        // Checking if the given game exists in the Cache
        if (memberService.getMembersOfGameIfPresent(receiveRequest.member) == null)
            return SendRequest(true, "Deine GameID ist nicht in der Datenbank enthalten!", 0)

        // Checking if the given member is contained in the game
        val currentGameMembers: Array<Int> = memberService.getMembersOfGameIfPresent(receiveRequest.game)!!
        if (!currentGameMembers.contains(receiveRequest.member))
            return SendRequest(true, "Deine MemberID ist nicht deinem Spiel zugehörig", 0)

        // Calculating the points Thrown in the roll
        var points = 0;
        receiveRequest
            .toIntArray()
            .forEach { points += it }

        // Returning the Result
        return SendRequest(false, "Punktzahl: ", points)
    }


}
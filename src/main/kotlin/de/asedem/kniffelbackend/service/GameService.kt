package de.asedem.kniffelbackend.service

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import de.asedem.kniffelbackend.model.ReceiveRequest
import de.asedem.kniffelbackend.model.SendRequest
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class GameService {

    private val allGameMembers: Cache<Int, Array<Int>> = Caffeine.newBuilder()
        .expireAfterWrite(1, TimeUnit.HOURS)
        .build()

    /**
     * Calculates the Dice results given in the [receiveRequest] to an output,
     * that can be sent via HttpRequests to the Client
     * @return the SendRequest that will be returned to the Client
     */
    fun calculateDices(receiveRequest: ReceiveRequest): SendRequest {

        // Checking if the given game exists in the Cache
        if (allGameMembers.getIfPresent(receiveRequest.member) == null) return SendRequest(true,
            "Deine GameID ist nicht in der Datenbank enthalten!", 0)

        // Checking if the given member is contained in the game
        val currentGameMembers: Array<Int> = allGameMembers.getIfPresent(receiveRequest.game)!!
        if (!currentGameMembers.contains(receiveRequest.member)) return SendRequest(true,
            "Deine MemberID ist nicht deinem Spiel zugehörig", 0)

        // Calculating the points Thrown in the roll
        var points = 0;
        receiveRequest
            .toIntArray()
            .forEach { points += it }

        // Returning the Result
        return SendRequest(false, "Punktzahl: ", points)
    }

    /**
     * Adds a group of [members] to the [game]
     */
    fun addMembersToGame(game: Int, members: Array<Int>) {
        allGameMembers.put(game, members)
    }
}
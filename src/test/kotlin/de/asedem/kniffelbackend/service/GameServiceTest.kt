package de.asedem.kniffelbackend.service

import de.asedem.kniffelbackend.model.ReceiveRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameServiceTest {

    private lateinit var gameService: GameService
    private val memberArray1: Array<Int> = arrayOf(0, 1)
    private val memberArray2: Array<Int> = arrayOf(2, 3)

    @BeforeEach
    fun createNewGameService() {
        gameService = GameService()
    }

    @Test
    fun shouldReturnErrorIfTheMatchDontExists() {
        val sendRequest = gameService
            .calculateDices(ReceiveRequest(1, 2, 2, 5, 6, 0, 0));
        assertEquals(sendRequest.error, true)
        assertEquals(sendRequest.message, "Deine GameID ist nicht in der Datenbank enthalten!")
        assertEquals(sendRequest.points, 0)
    }

    @Test
    fun shouldReturnErrorIfTheMatchDontContainsTheMember() {
        gameService.memberService.addMembersToGame(0, memberArray2)
        val sendRequest = gameService
            .calculateDices(ReceiveRequest(1, 2, 2, 5, 6, 0, 0));
        assertEquals(sendRequest.error, true)
        assertEquals(sendRequest.message, "Deine MemberID ist nicht deinem Spiel zugehörig")
        assertEquals(sendRequest.points, 0)
    }

    @Test
    fun shouldCalculateAllDicesIfNoPatternMatches() {
        gameService.memberService.addMembersToGame(0, memberArray1)
        assertEquals(gameService
            .calculateDices(ReceiveRequest(1,2,2,5,6, 0, 0)).points, 16)
    }
}
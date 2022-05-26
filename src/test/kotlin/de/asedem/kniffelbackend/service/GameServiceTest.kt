package de.asedem.kniffelbackend.service

import de.asedem.kniffelbackend.model.ThrowDTO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameServiceTest {

    private lateinit var gameService: GameService

    @BeforeEach
    fun createNewGameService() {
        gameService = GameService()
    }

    @Test
    fun shouldReturnErrorIfTheMatchDontExists() {
        val sendRequest = gameService
            .calculateDices(ThrowDTO(1, 2, 2, 5, 6, 0, 0))
        assertEquals(sendRequest.error, true)
        assertEquals(sendRequest.message, "Deine GameID ist nicht in der Datenbank enthalten!")
        assertEquals(sendRequest.points, 0)
    }

    @Test
    fun shouldReturnErrorIfTheMatchDontContainsTheMember() {
        gameService.memberService.addMembersToGame(0, arrayOf(2, 3))
        val sendRequest = gameService
            .calculateDices(ThrowDTO(1, 2, 2, 5, 6, 0, 0))
        assertEquals(sendRequest.error, true)
        assertEquals(sendRequest.message, "Deine MemberID ist nicht deinem Spiel zugehörig")
        assertEquals(sendRequest.points, 0)
    }

    @Test
    fun shouldCalculateAllDicesIfNoPatternMatches() {
        gameService.memberService.addMembersToGame(0, arrayOf(0, 1))
        val sendRequest = gameService
            .calculateDices(ThrowDTO(1,2,2,5,6, 0, 0))
        assertEquals(sendRequest.error, false)
        assertEquals(sendRequest.points, 16)
    }

    @Test
    fun shouldFindAPlayerInAGameHeJoinedNotDirectlyAfterCreation() {
        gameService.memberService.addMembersToGame(0, arrayOf(1))
        var sendRequest = gameService
                .calculateDices(ThrowDTO(1, 2, 2, 5, 6, 0, 0))
        assertEquals(sendRequest.error, true)
        assertEquals(sendRequest.message, "Deine MemberID ist nicht deinem Spiel zugehörig")
        assertEquals(sendRequest.points, 0)
        gameService.memberService.addMemberToGame(0, 0)
        sendRequest = gameService
            .calculateDices(ThrowDTO(1, 2, 2, 5, 6, 0, 0))
        assertEquals(sendRequest.error, false)
        assertEquals(sendRequest.points, 16)
    }
}
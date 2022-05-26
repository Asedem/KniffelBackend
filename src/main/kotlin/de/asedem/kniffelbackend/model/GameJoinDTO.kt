package de.asedem.kniffelbackend.model

/**
 * A DTO (DataTransferObject) which will be sent by the client to send a join request
 * to a specific game to the backend
 *
 * @property game The game that the [member] wants to join
 * @property member The member who wants to join the [game]
 */
class GameJoinDTO(
    val game: Int,
    val member: Int
) { }
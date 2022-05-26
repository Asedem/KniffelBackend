package de.asedem.kniffelbackend.model

/**
 * A DTO (DataTransferObject) which will be sent by the backend to give the points required
 * on the previous throw to the client
 *
 * @property error is true if the throw was incorrect (happens often if the game or the member is wrong)
 * @property message a message that the client receives (Could be good or the message to the [error])
 * @property points the points that the member threw (is 0 if there is an [error])
 */
class ThrowAnswerDTO(
    val error: Boolean,
    val message: String,
    val points: Int
) { }
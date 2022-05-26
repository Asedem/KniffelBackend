package de.asedem.kniffelbackend.model

/**
 * A DTO (DataTransferObject) which will be sent by the client to give the results of the
 * thrown dices to the backend
 *
 * @property one The number rolled by the first dice
 * @property two The number rolled by the second dice
 * @property three The number rolled by the third dice
 * @property four The number rolled by the fourth dice
 * @property five The number rolled by the fifth dice
 *
 * @property game The game where the dices are thrown
 * @property member The member of the [game] who threw the dices
 */
class ThrowDTO(
    private val one: Int,
    private val two: Int,
    private val three: Int,
    private val four: Int,
    private val five: Int,
    val game: Int,
    val member: Int
) {

    /**
     * A method to convert the numbers o the dices to one Int Array to work better with tha values
     *
     * @return The IntArray that contains all values from the dices
     */
    fun toIntArray(): Array<Int> {
        return arrayOf(one, two, three, four, five)
    }
}
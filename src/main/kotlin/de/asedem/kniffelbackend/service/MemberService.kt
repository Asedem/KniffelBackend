package de.asedem.kniffelbackend.service

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class MemberService {

    private val allGameMembers: Cache<Int, Array<Int>> = Caffeine.newBuilder()
        .expireAfterWrite(1, TimeUnit.HOURS)
        .build()

    /**
     * Adds a group of [members] to the [game]
     */
    fun addMembersToGame(game: Int, members: Array<Int>) {
        allGameMembers.put(game, members)
    }

    /**
     * Adds a [member] to the [game]
     */
    fun addMemberToGame(game: Int, member: Int) {
        if (getMembersOfGameIfPresent(game) == null)
            allGameMembers.put(game, arrayOf(member))

        TODO("Hier kommt noch rein, was passiert, wenn man" +
                "schon user in der Liste/Array hat und einen hinzu" +
                "fügen möchte... (Array -> List)")
    }

    /**
     * Gets all the members of a [game]
     *
     * @return the members of the [game]
     */
    fun getMembersOfGameIfPresent(game: Int): Array<Int>? {
        return allGameMembers.getIfPresent(game)
    }
}
package de.asedem.kniffelbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KniffelBackendApplication

fun main(args: Array<String>) {
    runApplication<KniffelBackendApplication>(*args)
}

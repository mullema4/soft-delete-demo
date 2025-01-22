package dk.cngroup.softdelete

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SoftDeleteDemoApplication

fun main(args: Array<String>) {
    runApplication<SoftDeleteDemoApplication>(*args)
}

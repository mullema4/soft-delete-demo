package dk.cngroup.softdelete

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer(private val productRepository: ProductRepository) : CommandLineRunner {
    override fun run(vararg args: String) {
        val tieFighter = Product(1, "TIE Fighter")
        val deathStar = Product(2, "Death Star")
        val starDestroyer = Product(3, "Star Destroyer")
        productRepository.saveAll(arrayListOf(tieFighter, deathStar, starDestroyer))
    }
}
package dk.cngroup.softdelete

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(private val productRepository: ProductRepository) {
    @GetMapping
    fun listProducts(): Iterable<Product> = productRepository.findAll()

    @GetMapping("/deactivated")
    fun listDeactivatedProducts(): Iterable<Product> = productRepository.findSoftDeleted()

    @GetMapping("/deactivate/{id}")
    @Transactional
    fun deactivateProduct(@PathVariable("id") id: Long) =
        productRepository.findByIdOrNull(id)
            ?.let { productRepository.delete(it) }

    @GetMapping("/reactivate")
    @Transactional
    fun reactivateAllProducts() = productRepository.reactivateAll()
}
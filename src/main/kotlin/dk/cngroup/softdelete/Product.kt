package dk.cngroup.softdelete

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.SoftDelete
import org.hibernate.annotations.SoftDeleteType
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

@Entity
@SoftDelete(strategy = SoftDeleteType.ACTIVE)
class Product(
    @Id
    var id: Long,
    var name: String,
    @Column(updatable = false, insertable = false)
    var active: Boolean = true
)

interface ProductRepository : CrudRepository<Product, Long> {

    @Query("select * from Product p where p.active = false", nativeQuery = true)
    fun findSoftDeleted(): List<Product>

    // Query to reactivate all soft-deleted entries
    @Modifying
    @Query("update Product p set p.active = true where p.active = false", nativeQuery = true)
    fun reactivateAll(): Int
}
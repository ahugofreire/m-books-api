package mercadolivro.com.repository

import mercadolivro.com.model.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Int?> {
    fun findByNameContaining(name: String): List<Customer>
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Customer?
}

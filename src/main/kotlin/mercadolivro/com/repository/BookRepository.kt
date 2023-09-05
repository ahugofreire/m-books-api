package mercadolivro.com.repository

import mercadolivro.com.enums.BookStatus
import mercadolivro.com.model.Book
import mercadolivro.com.model.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Int> {
    fun findByStatus(status: BookStatus, pageable: Pageable): Page<Book>
    fun findByCustomer(customer: Customer): List<Book>

}

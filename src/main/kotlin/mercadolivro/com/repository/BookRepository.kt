package mercadolivro.com.repository

import mercadolivro.com.enums.BookStatus
import mercadolivro.com.model.Book
import mercadolivro.com.model.Customer
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Int> {
    fun findByStatus(status: BookStatus): List<Book>
    fun findByCustomer(customer: Customer): List<Book>
}
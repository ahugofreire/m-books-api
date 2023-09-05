package mercadolivro.com.service

import mercadolivro.com.enums.BookStatus
import mercadolivro.com.exception.NotFoundException
import mercadolivro.com.model.Book
import mercadolivro.com.model.Customer
import mercadolivro.com.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: Book) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<Book> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<Book> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Int): Book {
        return bookRepository.findById(id).orElseThrow{ NotFoundException("Book id: [$id] not found in database", "ML-0001")}
    }

    fun deleteById(id: Int) {
        val book = this.findById(id)
        book.status = BookStatus.CANCELADO

        this.update(book)
    }

    fun update(book: Book) {
        this.bookRepository.save(book)
    }

    fun deleteByCustomer(customer: Customer) {
        val  books = bookRepository.findByCustomer(customer)
        for(book in books) {
           book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }
}

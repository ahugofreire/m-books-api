package mercadolivro.com.service

import mercadolivro.com.enums.BookStatus
import mercadolivro.com.model.Book
import mercadolivro.com.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: Book) {
        bookRepository.save(book)
    }

    fun findAll(): List<Book> {
        return bookRepository.findAll().toList()
    }

    fun findActives(): List<Book> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
    }

    fun findById(id: Int): Book {
        return bookRepository.findById(id).orElseThrow()
    }

    fun deleteById(id: Int) {
        val book = this.findById(id)
        book.status = BookStatus.CANCELADO

        this.update(book)
    }

    fun update(book: Book) {
        this.bookRepository.save(book)
    }
}
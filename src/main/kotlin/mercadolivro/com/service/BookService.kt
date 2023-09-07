package mercadolivro.com.service

import mercadolivro.com.enums.BookStatus
import mercadolivro.com.enums.Errors
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
        return bookRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML1001.message.format(id), Errors.ML1001.code)}
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

    fun findByIds(bookIds: Set<Int>): List<Book> {
        return bookRepository.findAllById(bookIds).toList()
    }

    fun purchase(books: MutableList<Book>) {
        books.map {
            it.status = BookStatus.VENDIDO
        }
        bookRepository.saveAll(books)
    }
}

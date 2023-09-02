package mercadolivro.com.controller

import mercadolivro.com.controller.dto.BookInputDto
import mercadolivro.com.controller.dto.PutBookInputDto
import mercadolivro.com.extension.toBook
import mercadolivro.com.model.Book
import mercadolivro.com.service.BookService
import mercadolivro.com.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("books")
class BookController(
    var bookService: BookService,
    var customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody request: BookInputDto) {
        val customer = customerService.getCustomer(request.customerId)
        bookService.create(request.toBook(customer))
    }

    @GetMapping
    fun findAllBooks(): List<Book> {
        return bookService.findAll()
    }

    @GetMapping("/active")
    fun findActives(): List<Book> {
        return bookService.findActives()
    }
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Book {
        return bookService.findById(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBookById(@PathVariable id: Int) {
        return bookService.deleteById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookInputDto) {
        val bookSaved = bookService.findById(id)
        return bookService.update(book.toBook(bookSaved))
    }
}
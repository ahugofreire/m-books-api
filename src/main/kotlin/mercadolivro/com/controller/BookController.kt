package mercadolivro.com.controller

import jakarta.validation.Valid
import mercadolivro.com.controller.dto.BookInputDto
import mercadolivro.com.controller.dto.PutBookInputDto
import mercadolivro.com.controller.response.BookResponse
import mercadolivro.com.extension.toBook
import mercadolivro.com.extension.toResponse
import mercadolivro.com.service.BookService
import mercadolivro.com.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("books")
class BookController(
    var bookService: BookService,
    var customerService: CustomerService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody @Valid request: BookInputDto) {
        val customer = customerService.findCustomerById(request.customerId)
        bookService.create(request.toBook(customer))
    }

    @GetMapping
    fun findAllBooks(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        return bookService.findAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/active")
    fun findActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        return bookService.findActives(pageable).map { it.toResponse() }
    }
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
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

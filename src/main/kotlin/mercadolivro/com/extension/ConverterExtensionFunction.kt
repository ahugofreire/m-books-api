package mercadolivro.com.extension

import mercadolivro.com.controller.dto.BookInputDto
import mercadolivro.com.controller.dto.CustomerInputDTO
import mercadolivro.com.controller.dto.PutBookInputDto
import mercadolivro.com.controller.response.CustomerResponse
import mercadolivro.com.enums.BookStatus
import mercadolivro.com.model.Book
import mercadolivro.com.model.Customer
import mercadolivro.com.enums.CustomerStatus
import mercadolivro.com.controller.response.BookResponse

fun CustomerInputDTO.toCustomer() : Customer {
    return Customer(name = this.name, email = this.email, password = this.password, status = CustomerStatus.ATIVO)
}

fun CustomerInputDTO.toCustomer(previousCustomer: Customer) : Customer {
    return Customer(
        id = previousCustomer.id,
        name = this.name ?: previousCustomer.name,
        email = this.email ?: previousCustomer.email,
        password = this.password ?: previousCustomer.password,
        status = previousCustomer.status
    )
}

fun BookInputDto.toBook(customer: Customer) : Book {
    return Book(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}
fun PutBookInputDto.toBook(bookPrevious: Book) : Book {
    return Book(
        id = bookPrevious.id,
        name = this.name ?: bookPrevious.name,
        price = this.price ?: bookPrevious.price,
        status = bookPrevious.status,
        customer = bookPrevious.customer
    )
}

fun Customer.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun Book.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}

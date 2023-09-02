package mercadolivro.com.extension

import mercadolivro.com.controller.dto.BookInputDto
import mercadolivro.com.controller.dto.CustomerInputDTO
import mercadolivro.com.controller.dto.PutBookInputDto
import mercadolivro.com.enums.BookStatus
import mercadolivro.com.model.Book
import mercadolivro.com.model.Customer

fun CustomerInputDTO.toCustomer() : Customer {
    return Customer(name = this.name, email = this.email)
}

fun CustomerInputDTO.toCustomer(id: Int) : Customer {
    return Customer(id = id, name = this.name, email = this.email)
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

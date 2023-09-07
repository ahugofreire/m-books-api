package mercadolivro.com.controller.mapper

import mercadolivro.com.controller.dto.PurchaseInputDto
import mercadolivro.com.model.Purchase
import mercadolivro.com.service.BookService
import mercadolivro.com.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val bookService: BookService,
    private val customerService: CustomerService
) {
    fun toModel(request: PurchaseInputDto): Purchase {
        val customer = customerService.findCustomerById(request.customerId)
        val books = bookService.findByIds(request.bookIds)

        return Purchase(
            customer = customer,
            books = books.toMutableList(),
            price = books.sumOf { it.price }
        )
    }
}

package mercadolivro.com.controller.response

import mercadolivro.com.enums.BookStatus
import mercadolivro.com.model.Customer
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: Customer? = null,
    var status: BookStatus? = null,
)

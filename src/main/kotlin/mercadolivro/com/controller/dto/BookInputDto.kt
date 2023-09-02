package mercadolivro.com.controller.dto

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal

data class BookInputDto(
    var name: String,
    var price: BigDecimal,

    @JsonAlias("customer_id")
    var customerId: Int
)
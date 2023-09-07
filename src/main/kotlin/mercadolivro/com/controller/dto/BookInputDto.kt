package mercadolivro.com.controller.dto

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class BookInputDto(
    @field:NotEmpty(message = "Name must be valid")
    var name: String,
    @field:NotNull(message = "Price must be valid")
    var price: BigDecimal,

    @JsonAlias("customer_id")
    var customerId: Int
)

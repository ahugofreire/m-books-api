package mercadolivro.com.controller.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

class PutBookInputDto (
    var name: String?,
    var price: BigDecimal?,
)

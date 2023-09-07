package mercadolivro.com.controller.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import mercadolivro.com.validation.EmailAvailable

data class CustomerInputDTO (
    @field:NotEmpty(message = "Name must be valid")
    var name: String,
    @field:Email(message = "E-mail must be valid")
    @EmailAvailable
    var email: String,
)

package mercadolivro.com.controller.response

import mercadolivro.com.enums.CustomerStatus
data class CustomerResponse(
    var id: Int? = null,
    var name: String,
    var email: String,
    var status: CustomerStatus,
)

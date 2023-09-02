package mercadolivro.com.extension

import mercadolivro.com.controller.dto.CustomerInputDTO
import mercadolivro.com.model.Customer

fun CustomerInputDTO.toCustomer() : Customer {
    return Customer(name = this.name, email = this.email)
}

fun CustomerInputDTO.toCustomer(id: Int) : Customer {
    return Customer(id = id, name = this.name, email = this.email)
}
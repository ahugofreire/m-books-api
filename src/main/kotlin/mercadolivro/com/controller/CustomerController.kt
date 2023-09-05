package mercadolivro.com.controller

import mercadolivro.com.controller.dto.CustomerInputDTO
import mercadolivro.com.controller.response.CustomerResponse
import mercadolivro.com.extension.toCustomer
import mercadolivro.com.extension.toResponse
import mercadolivro.com.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService
) {
    @GetMapping
    fun getCustomers(@RequestParam name: String?): List<CustomerResponse> {
        return customerService.getCustomers(name).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: CustomerInputDTO) {
        return customerService.create(customer.toCustomer())
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
        return customerService.findCustomerById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: CustomerInputDTO) {
        val customerSaved = customerService.findCustomerById(id)
        return customerService.updateCustomer(customer.toCustomer(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Int) {
        return customerService.deleteCustomer(id)
    }
}

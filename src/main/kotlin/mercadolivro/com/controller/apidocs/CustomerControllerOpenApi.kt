package mercadolivro.com.controller.apidocs

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import mercadolivro.com.controller.dto.CustomerInputDTO
import mercadolivro.com.controller.response.CustomerResponse
import org.springframework.data.domain.Page
import java.awt.print.Pageable

@Tag(name = "Customer", description = "this API provide methods for operations with customers")
interface CustomerControllerOpenApi {

    @Operation(summary = "Find all customers, also find all containing a string on name")
    fun getAllCustomer(name: String?, pageable: Pageable): Page<CustomerResponse>

    @Operation(summary = "Find a customer by id")
    fun getCustomer(id: Int): CustomerResponse

    @Operation(summary = "Create new customer")
    fun createCustomer(customer: CustomerInputDTO): CustomerResponse

    @Operation(summary = "Update existing customer")
    fun updateCustomer(id: Int, customer: CustomerInputDTO): CustomerResponse

    @Operation(summary = "Delete a customer by id")
    fun deleteCustomer(id: Int)
}

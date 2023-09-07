package mercadolivro.com.service

import mercadolivro.com.model.Customer
import mercadolivro.com.enums.CustomerStatus
import mercadolivro.com.enums.Errors
import mercadolivro.com.exception.NotFoundException
import mercadolivro.com.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    var customerRepository: CustomerRepository,
    var bookService: BookService
) {

    fun getCustomers(name: String?): List<Customer> {
        name?.let {
            return customerRepository.findByNameContaining(name)
        }
        return customerRepository.findAll().toList()
    }
    fun create (customer: Customer) {
        customerRepository.save(customer)

//        val id = if(customers.isEmpty()) {
//            1
//        } else {
//            customers.last().id!!.toInt() + 1
//        }
//
//        customer.id = id
//        customers.add(customer)
//        return
    }

    fun findCustomerById(id: Int): Customer {
        return customerRepository.findById(id)
            .orElseThrow{ NotFoundException(Errors.ML1101.message.format(id), Errors.ML1101.code)}
    }

    fun updateCustomer(customer: Customer) {

        if(!customerRepository.existsById(customer.id!!)){
            throw Exception("Err in update")
        }

        customerRepository.save(customer)
    }

    fun deleteCustomer(id: Int) {
        val customer = this.findCustomerById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO

        customerRepository.save(customer)
    }

}

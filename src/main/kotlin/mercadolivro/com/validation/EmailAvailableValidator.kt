package mercadolivro.com.validation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import mercadolivro.com.service.CustomerService

class EmailAvailableValidator(var customerService: CustomerService): ConstraintValidator<EmailAvailable, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrEmpty()) {
            return false
        }
        return customerService.emailAvailable(value)
    }

}

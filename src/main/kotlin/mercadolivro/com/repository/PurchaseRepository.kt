package mercadolivro.com.repository

import mercadolivro.com.model.Purchase
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository: CrudRepository<Purchase, Int> {
}

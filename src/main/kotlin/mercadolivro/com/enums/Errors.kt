package mercadolivro.com.enums

enum class Errors(val code: String, val message: String ) {
    ML0001("ML-0001", "Invalid Request"),
    ML1001("ML-1001", "Book id: [%s] not found in database"),
    ML1002("ML-1002", "Cannot update book with status [%s]"),
    ML1101("ML-1101", "Customer id: [%s] not found in database")
}

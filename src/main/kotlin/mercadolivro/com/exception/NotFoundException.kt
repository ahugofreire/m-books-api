package mercadolivro.com.exception

class NotFoundException(override val message: String, var errorCode: String): Exception() {
}

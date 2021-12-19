package domain.models




data class UserDomainModel(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressModel,
    val phone: String,
    val website: String,
    val company: CompanyData
)

data class AddressModel(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoModel
)

data class GeoModel(
    val lat: String,
    val lng: String
)

data class CompanyData(
    val name: String,
    val catchPhrase: String,
    val bs: String
)

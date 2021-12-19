package data.extensions

import data.*
import domain.models.PostDomainModel
import domain.models.UserDomainModel

fun UserModel.toUserDomainModel(): UserDomainModel{

    return UserDomainModel(
        id,
        name,
        username,
        email,
        address.toAddressDomainModel(),
        phone,
        website,
        company.toCompanyDomain()
    )
}

fun AddressModel.toAddressDomainModel(): domain.models.AddressModel{
    return domain.models.AddressModel(
        suite,
        street,
        city,
        zipcode,
        geo.toGeoDomainModel()
    )
}

fun GeoModel.toGeoDomainModel(): domain.models.GeoModel{
    return domain.models.GeoModel(
        lat, lng
    )
}

fun CompanyData.toCompanyDomain(): domain.models.CompanyData{
    return domain.models.CompanyData(
        name, catchPhrase, bs
    )
}


fun PostModel.toPostDomainModel(): PostDomainModel{
    return PostDomainModel(id, title, body, userId)
}

fun PostDomainModel.toPostModel(): PostModel{
    return PostModel(id, title, body, userId)
}
package org.sid.coreapi.dtos

/*Intialialiser les parameters pour creer un constructeur avec parameters et sans parameters
*
* */

data class CustomerRequestDto(
        var name: String="",
        var email:String=""
)

data class CustomerUpdateRequestDto(
        var customerId:String="",
        var name: String="",
        var email:String=""
)
data class CustomerDeleteRequestDto(
        var customerId:String="",
)
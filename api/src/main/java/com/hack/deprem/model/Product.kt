package com.hack.deprem.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator

@Entity
data class Product(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val uuid: String? ="",
        val name: String? = "",
        val productName: String,
        val number: Int,
        val isHuman: Boolean,
        val phoneNumber: String? = ""
){
        constructor(name: String, productName: String, number: Int, isHuman: Boolean) : this(
                null,
                name,
                productName,
                number,
                isHuman,
                null
        )
        constructor(name: String, productName: String, number: Int, isHuman: Boolean, phoneNumber: String) : this(
                null,
                name,
                productName,
                number,
                isHuman,
                phoneNumber
        )
}

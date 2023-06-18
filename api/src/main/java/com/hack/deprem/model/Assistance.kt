package com.hack.deprem.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import org.hibernate.annotations.GenericGenerator

@Entity
data class Assistance(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val uuid: String? ="",
        @OneToOne
        val product: Product,
        val fromCity: Int,
        val toLocation: String? = "" //Todo
){
        constructor(product: Product, from:Int):this(
                null,
                product,
                from,
                null
        )
        constructor(product: Product, from: Int, to: String) : this(
                null,
                product,
                from,
                to
        )
}

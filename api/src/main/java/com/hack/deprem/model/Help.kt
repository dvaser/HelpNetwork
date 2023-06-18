package com.hack.deprem.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
data class Help(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val uuid: String? ="",
        val location: String,//Todo
        @OneToOne
        val product: Product,
        var status: Short
){
        constructor(location: String, product: Product) : this(
                null,
                location,
                product,
                0
        )
}

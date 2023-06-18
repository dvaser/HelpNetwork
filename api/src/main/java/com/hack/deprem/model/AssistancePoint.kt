package com.hack.deprem.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
data class AssistancePoint(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val uuid: String? ="",
        @Column(unique = true)
        val location: String, //Todo
        @OneToMany
        val stock: List<Product>? = emptyList(),
        @OneToMany
        val onRoad: List<Product>? = emptyList(),
        @OneToMany
        val need: List<Product>? = emptyList(),

){
        constructor(location: String) : this(
                null,
                location,
                null,
                null,
                null
        )

        constructor(location: String, stock: List<Product>, onRoad: List<Product>, need: List<Product>) : this(
                null,
                location,
                stock,
                onRoad,
                need
        )

}

package br.com.zup.carro

import java.lang.annotation.*
import java.lang.annotation.Retention
import java.lang.annotation.Target
import javax.validation.Constraint


@MustBeDocumented
@Target(
    ElementType.FIELD,
    ElementType.CONSTRUCTOR
)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = [PlacaValidator::class])


annotation class Placa(
    val message: String = "Placa com formato inválido"
)

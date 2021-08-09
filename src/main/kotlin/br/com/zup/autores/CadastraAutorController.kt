package br.com.zup.autores

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastrar(@Body @Valid request: NovoAutorRequest){
        println("Requisição => ${request}")

        val autor = request.paraAutor()
        autorRepository.save(autor)

        println("Autor => ${autor.nome}")
    }
}
package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid
import javax.validation.constraints.Email

@Validated
@Controller("/autores")
class CadastraAutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastrar(@Body @Valid request: NovoAutorRequest): HttpResponse<Any>{
        println("Requisição => ${request}")
        val autor: Autor = request.paraAutor()
        var autorRespository: Autor = autorRepository.save(autor)
        println("Autor => ${autor.nome}")
        val resposta = DetalheAutorResponse(autorRespository)
        val uri = UriBuilder.of("autores/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(uri)
    }

    @Get
    fun listarTodos(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if(email.isBlank()){
            val autores =  autorRepository.findAll()
            val resposta = autores.map { autor -> DetalheAutorResponse(autor) }
            return HttpResponse.ok(resposta)
        }
        val autorRecuperado = autorRepository.findByEmail(email)
        if(autorRecuperado.isEmpty){
            return HttpResponse.notFound()
        }
        val autor = autorRecuperado.get()
        return HttpResponse.ok(DetalheAutorResponse(autor))

    }

    @Put("/{id}")
    fun atualizarAutor(@PathVariable id: Long, descricao: String): HttpResponse<Any>{
        val possivelAutor = autorRepository.findById(id)

        if(possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        autor.descricao = descricao
        autorRepository.update(autor)

        return HttpResponse.ok(DetalheAutorResponse(autor))
    }

    @Delete("/{id}")
    fun deletarAutor(@PathVariable id: Long): HttpResponse<Any>{
        var autorRecuperado = autorRepository.findById(id)
        if(autorRecuperado.isEmpty){
            return HttpResponse.notFound()
        }
        val autor = autorRecuperado.get()
        autorRepository.delete(autor)
        return HttpResponse.ok()
    }
}
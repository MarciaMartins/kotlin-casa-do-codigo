package br.com.zup.autores


import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastrar(@Body @Valid request: NovoAutorRequest): HttpResponse<DetalheAutorResponse>{
        println("Requisição => ${request}")
        val autor: Autor = request.paraAutor()
        var autorRespository: Autor = autorRepository.save(autor)
        println("Autor => ${autor.nome}")
        val resposta = DetalheAutorResponse(autorRespository)
        return HttpResponse.ok(resposta)
    }

    @Get
    fun listarTodos(): HttpResponse<List<DetalheAutorResponse>> {
        val autores =  autorRepository.findAll()
        val resposta = autores.map { autor -> DetalheAutorResponse(autor) }
        return HttpResponse.ok(resposta)
    }
}
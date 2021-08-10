package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import javax.transaction.Transactional


@Controller("/autoresQuery")
class BuscaAutorQueryExplicitaController (val autorRepository: AutorRepository){

    @Transactional
    @Get
    fun buscaAutor(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if(email.isBlank()){
            var autoresLista = autorRepository.findAll()
            var resposta = autoresLista.map { autor -> DetalheAutorResponse(autor) }
            return HttpResponse.ok(resposta)
        }

        var autorResposta = autorRepository.buscaPorEmail(email)
        if(autorResposta.isEmpty){
            return HttpResponse.notFound()
        }
        return HttpResponse.ok(DetalheAutorResponse(autorResposta.get()))



    }
}
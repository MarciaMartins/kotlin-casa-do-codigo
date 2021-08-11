package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
<<<<<<< HEAD
import javax.transaction.Transactional
=======

>>>>>>> kotlin-casa-do-codigo/master


@Controller("/autoresQuery")
class BuscaAutorQueryExplicitaController (val autorRepository: AutorRepository){

<<<<<<< HEAD
    @Transactional
=======
>>>>>>> kotlin-casa-do-codigo/master
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
package br.com.zup.autores

import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Embeddable
class Endereco(enderecoCleintResponse: EnderecoCleintResponse) {
    val rua = enderecoCleintResponse.logradouro
    val cidade = enderecoCleintResponse.localidade
    val estado = enderecoCleintResponse.uf

}

// ContatoRepository.java
package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Contato;

public interface ContatoRepository {
    Resultado<Contato> cadastrar(String nome, String email, String telefone);

    Resultado<List<Contato>> listarTodos();

    Resultado<Contato> buscarPorNome(String nome);

    Resultado<Contato> buscarNomeMaisComprido();
}

package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Contato;

public interface ContatoRepository {
    Resultado<Contato> cadastrar(String nome, String email, String Telefone);
    Resultado<List<Contato>> listarTodos();
}

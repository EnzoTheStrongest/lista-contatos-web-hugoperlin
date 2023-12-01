package ifpr.pgua.eic.tads.contatos.model.daos;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Contato;

public interface ContatoDAO {
    Resultado<Contato> criar(Contato contato);

    Resultado<List<Contato>> listar();

    Resultado<Contato> buscarPorNome(String nome);

    Resultado<Contato> buscarNomeMaisComprido();
}

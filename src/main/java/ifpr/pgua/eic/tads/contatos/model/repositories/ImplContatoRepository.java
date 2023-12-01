// ImplContatoRepository.java
package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Contato;
import ifpr.pgua.eic.tads.contatos.model.daos.ContatoDAO;

public class ImplContatoRepository implements ContatoRepository {

    private ContatoDAO dao;

    public ImplContatoRepository(ContatoDAO dao) {
        this.dao = dao;
    }

    @Override
    public Resultado<Contato> cadastrar(String nome, String email, String telefone) {
        if (nome.isBlank() || nome.isEmpty()) {
            return Resultado.erro("Nome inválido!");
        }

        if (telefone.isBlank() || telefone.isEmpty()) {
            return Resultado.erro("Telefone inválido!");
        }

        if (email.isBlank() || email.isEmpty()) {
            return Resultado.erro("E-mail inválido!");
        }

        Contato contato = new Contato(nome, telefone, email);

        return dao.criar(contato);
    }

    @Override
    public Resultado<List<Contato>> listarTodos() {
        return dao.listar();
    }

    @Override
    public Resultado<Contato> buscarPorNome(String nome) {
        return dao.buscarPorNome(nome);
    }

    @Override
    public Resultado<Contato> buscarNomeMaisComprido() {
        return dao.buscarNomeMaisComprido();
    }
}

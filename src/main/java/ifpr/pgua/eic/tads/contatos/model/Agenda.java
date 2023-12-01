package ifpr.pgua.eic.tads.contatos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.daos.ContatoDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.TarefaDAO;

public class Agenda {
    private FabricaConexoes fabricaConexao;
    private ContatoDAO contatoDao;
    private TarefaDAO tarefaDao;
    private ArrayList<Tarefa> tarefas;
    private ArrayList<Contato> lista;

    public Agenda(FabricaConexoes fabricaConexao, ContatoDAO contatoDao, TarefaDAO tarefaDao) {
        this.fabricaConexao = fabricaConexao;
        this.contatoDao = contatoDao;
        this.tarefaDao = tarefaDao;
        this.tarefas = new ArrayList<>();
        this.lista = new ArrayList<>(); // Adicione esta linha
    }

    public List<Tarefa> getTarefas() {
        Resultado<List<Tarefa>> resultado = tarefaDao.listar();

        if (resultado.foiSucesso()) {
            return resultado.comoSucesso().getObj();
        } else {
            // Lidar com o erro, retornando uma lista vazia ou lançando uma exceção
            System.out.println("Erro ao obter lista de tarefas: " + resultado.getMsg());
            return new ArrayList<>();
        }
    }

    public String cadastrarTarefa(String titulo, String descricao) {
        if (titulo.isBlank() || titulo.isEmpty()) {
            return "Título inválido!";
        }

        if (descricao.isBlank() || descricao.isEmpty()) {
            return "Descrição inválida!";
        }

        Tarefa tarefa = new Tarefa(titulo, descricao);

        Resultado<Tarefa> resultado = tarefaDao.criar(tarefa);

        return resultado.getMsg();
    }

    public String cadastrar(String nome, String telefone, String email) {
        if (nome.isBlank() || nome.isEmpty()) {
            return "Nome inválido!";
        }

        if (telefone.isBlank() || telefone.isEmpty()) {
            return "Telefone inválido!";
        }

        if (email.isBlank() || email.isEmpty()) {
            return "E-mail inválido!";
        }

        Contato contato = new Contato(nome, telefone, email);

        Resultado<Contato> resultado = contatoDao.criar(contato);

        return resultado.getMsg();
    }

    public Contato buscarNomeMaisComprido() {
        Resultado<Contato> resultado = contatoDao.buscarNomeMaisComprido();

        if (resultado.foiSucesso()) {
            return resultado.comoSucesso().getObj();
        } else {
            System.out.println("Erro ao buscar contato com nome mais comprido: " + resultado.getMsg());
            return null;
        }
    }

    public String listar() {
        List<Contato> contatos = contatoDao.listar().comoSucesso().getObj();
        String texto = "Contatos Cadastrados:";

        for (Contato c : contatos) {
            texto += c.toString() + "<br/>";
        }

        return texto;
    }

}

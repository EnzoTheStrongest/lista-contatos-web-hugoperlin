package ifpr.pgua.eic.tads.contatos.model.daos;

import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.tads.contatos.model.FabricaConexoes;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTarefaDAO implements TarefaDAO {

    private FabricaConexoes fabricaConexao;

    public JDBCTarefaDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Resultado<Tarefa> criar(Tarefa tarefa) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("INSERT INTO oo_tarefas(titulo, descricao) VALUES (?, ?)");

            pstm.setString(1, tarefa.getTitulo());
            pstm.setString(2, tarefa.getDescricao());

            pstm.executeUpdate();

            con.close();
            return Resultado.sucesso("Tarefa cadastrada", tarefa);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<List<Tarefa>> listar() {
        ArrayList<Tarefa> lista = new ArrayList<>();
        try {
            Connection con = fabricaConexao.getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM oo_tarefas");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");

                Tarefa tarefa = new Tarefa(id, titulo, descricao);

                lista.add(tarefa);
            }
            con.close();
            return Resultado.sucesso("Tarefas carregadas", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}

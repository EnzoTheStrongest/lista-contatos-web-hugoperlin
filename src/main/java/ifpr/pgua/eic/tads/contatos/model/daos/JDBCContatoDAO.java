package ifpr.pgua.eic.tads.contatos.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Contato;
import ifpr.pgua.eic.tads.contatos.model.FabricaConexoes;

public class JDBCContatoDAO implements ContatoDAO {

    private FabricaConexoes fabricaConexao;

    public JDBCContatoDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Resultado<Contato> criar(Contato contato) {

        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con
                    .prepareStatement("INSERT INTO oo_contatos(nome,email,telefone) VALUES (?,?,?)");

            pstm.setString(1, contato.getNome());
            pstm.setString(2, contato.getEmail());
            pstm.setString(3, contato.getTelefone());

            pstm.executeUpdate();

            con.close();
            return Resultado.sucesso("Contato cadastrado", contato);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<List<Contato>> listar() {
        ArrayList<Contato> lista = new ArrayList<>();
        try {
            Connection con = fabricaConexao.getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM oo_contatos");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");

                Contato contato = new Contato(id, nome, email, telefone);

                lista.add(contato);
            }
            con.close();
            return Resultado.sucesso("Contatos carregados", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }

    }

    @Override
    public Resultado<Contato> buscarPorNome(String nome) {
        try {
            Connection con = fabricaConexao.getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM oo_contatos WHERE nome = ?");
            pstm.setString(1, nome);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");

                Contato contato = new Contato(id, nome, email, telefone);

                con.close();
                return Resultado.sucesso("Contato encontrado", contato);
            } else {
                con.close();
                return Resultado.erro("Contato não encontrado");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<Contato> buscarNomeMaisComprido() {
        try {
            Connection con = fabricaConexao.getConnection();
            PreparedStatement pstm = con
                    .prepareStatement("SELECT * FROM oo_contatos ORDER BY LENGTH(nome) DESC LIMIT 1");

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");

                Contato contato = new Contato(id, nome, email, telefone);

                con.close();
                return Resultado.sucesso("Contato encontrado", contato);
            } else {
                con.close();
                return Resultado.erro("Contato não encontrado");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

}

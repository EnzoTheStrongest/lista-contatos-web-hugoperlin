package ifpr.pgua.eic.tads.contatos.model.daos;

import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;

import java.util.List;

public interface TarefaDAO {
    Resultado<Tarefa> criar(Tarefa tarefa);

    Resultado<List<Tarefa>> listar();
}

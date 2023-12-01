package ifpr.pgua.eic.tads.contatos.controllers;

import java.util.List;
import ifpr.pgua.eic.tads.contatos.model.Agenda;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ListTarefaController {

    private Agenda agenda;

    public ListTarefaController(Agenda agenda) {
        this.agenda = agenda;
    }

    public Handler get = (Context ctx) -> {

        List<Tarefa> lista = agenda.getTarefas();

        String html = "<html><head><meta charset=\"UTF-8\"></head><body><h1>Lista de Contatos</h1><ul>";

        for (Tarefa t : lista) {
            html += "<li>" + t.toString() + "</li>";
        }

        html += "</ul><a href=\"/\">Voltar</a></body></html>";
        ctx.html(html);
    };
}

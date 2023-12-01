package ifpr.pgua.eic.tads.contatos.controllers;

import ifpr.pgua.eic.tads.contatos.model.Agenda;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AddTarefaController {

    private Agenda agenda;

    public AddTarefaController(Agenda agenda) {
        this.agenda = agenda;
    }

    public Handler get = (Context ctx) -> {
        ctx.render("templates/addTarefa.html");
    };

    public Handler post = (Context ctx) -> {
        String titulo = ctx.formParam("titulo");
        String descricao = ctx.formParam("descricao");

        String resultado = agenda.cadastrarTarefa(titulo, descricao);
        ctx.html(resultado + "<br/><a href=\"/\">Voltar</a>");
    };
}

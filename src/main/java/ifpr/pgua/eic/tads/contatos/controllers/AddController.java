package ifpr.pgua.eic.tads.contatos.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.Contato;
import ifpr.pgua.eic.tads.contatos.model.repositories.ContatoRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AddController {

    private ContatoRepository repositorio;

    public AddController(ContatoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Handler get = (Context ctx) -> {
        ctx.render("templates/add.html");
    };

    public Handler post = (Context ctx) -> {
        String nome = ctx.formParam("nome");
        String email = ctx.formParam("email");
        String telefone = ctx.formParam("telefone");

        Resultado<Contato> resultado = repositorio.cadastrar(nome, email, telefone);

        ctx.html(resultado.getMsg() + "<br/><a href=\"/\">Voltar</a>");
    };
}

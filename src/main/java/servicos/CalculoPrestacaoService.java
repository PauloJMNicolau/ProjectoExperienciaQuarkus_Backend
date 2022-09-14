package servicos;

import casosUso.CalculoPrestacao;
import entidades.Erros;
import entidades.Financiamento;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;


/**
 * Classe de Serviços relativos ao Calculo de Prestações e armazenamento das mesmas
 */
@Path("/prestacoes")
public class CalculoPrestacaoService {

    /**
     * Serviço de Cálculo de valor da prestação mensal
     * @param mensalidades
     * @param valor
     * @param tipo
     * @return Resultado do serviço
     */
    @GET
    @Path("/getValor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getValorPrestacao(@QueryParam("mensalidades") Integer mensalidades,
                                      @QueryParam("valor") Double valor, @QueryParam("financiamento") Integer tipo){
        if(mensalidades == null || valor == null || tipo == null) {
            Erros erros = getErros(mensalidades, valor, tipo);
            return Response.status(Response.Status.BAD_REQUEST).entity(erros).build();
        }
        Financiamento financiamento = new Financiamento(mensalidades, valor, tipo);
        CalculoPrestacao calculo = new CalculoPrestacao(financiamento);
        Erros erros = calculo.calculoValorPrestacao();
        if(erros == null)
            return Response.ok().entity(financiamento).build();
        return Response.noContent().build();
    }

    /**
     * Serviço de armazenamento dos dados do Financiamento num ficheiro no servidor
     * @param financiamento
     * @return Resultado do serviço
     */
    @POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvarFinanciamento(Financiamento financiamento){
        if(financiamento == null){
            Erros erros = new Erros("Falta indicar o conteúdo a guardar");
            return Response.status(Response.Status.BAD_REQUEST).entity(erros).build();
        }
        try{
            File ficheiro = new File(Paths.get("").toAbsolutePath().normalize() +"/files/venda_"+ financiamento.getNome()+".csv");
            FileOutputStream ficheiroStream = new FileOutputStream(ficheiro);
            String linha = financiamento.toString();
            ficheiroStream.write(linha.getBytes());
        } catch (Exception e){
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    /**
     * Obter os erros de parametros
     * @param mensalidades
     * @param valor
     * @param tipo
     * @return Objeto com informação de Erros
     */
    private Erros getErros(Integer mensalidades, Double valor, Integer tipo){
        Erros erros = new Erros("ERRO: Falta indicar parametros" );
        if(mensalidades == null)
            erros.addErro("{mensalidades}");
        if(valor == null)
            erros.addErro("{valor}");
        if(tipo == null)
            erros.addErro("{financiamento}");
        return erros;
    }
}

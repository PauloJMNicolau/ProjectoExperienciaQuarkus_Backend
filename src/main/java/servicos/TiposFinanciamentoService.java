package servicos;

import enumeradores.TiposFinanciamento;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Serviços relacionado aos dados dos tipos de financiamento
 */
@Path("/financiamento")
public class TiposFinanciamentoService {

    /**
     * Serviço para obter os diferentes tipos de Financiamento
     * @return Lista com os Tipos de Financiamento
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTipos")
    public Response getTiposFinanciamento(){
        ArrayList<TiposFinanciamento> lista = new ArrayList<>(List.of(TiposFinanciamento.values()));
        return Response.ok(lista).build();
    }

    /**
     * Serviço para obter as mensalidades do tipo de financiamento serviço fornecido
     * @param tipo
     * @return Lista com as mensalidades disponiveis
     */
    @GET
    @Path("/getListaMensalidades/{tipo}")
    public List<Integer> getListaMensalidades(Integer tipo){
        if(tipo == null)
            tipo = 0;
        return TiposFinanciamento.getListaPrestacoesByTipo(TiposFinanciamento.getFinanciamentoByOrdering(tipo));
    }
}

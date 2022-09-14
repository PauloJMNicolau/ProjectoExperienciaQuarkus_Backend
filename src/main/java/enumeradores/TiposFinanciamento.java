package enumeradores;

import java.util.ArrayList;
import java.util.List;

//Enumerador com os Tipos de Financiamento Existentes
public enum TiposFinanciamento {
    EXTERNO("Externo"),
    INTERNO( "Interno");

    private final String designacao;

    TiposFinanciamento(String designacao){
        this.designacao = designacao;
    }

    /**
     * Obter o tipo de Financiamento com base na posição
     * @param posicao - posição do enumerador
     * @return Tipom de Financiamento correspondente
     */
    public static TiposFinanciamento getFinanciamentoByOrdering(Integer posicao){
        if(posicao== null)
            return TiposFinanciamento.EXTERNO;
        for(TiposFinanciamento tipo : TiposFinanciamento.values()){
            if(tipo.ordinal() == posicao)
                return tipo;
        }
        return TiposFinanciamento.EXTERNO;
    }

    /**
     * Obter a Lista de Prestações disponiveis para cada tipo de financiamento
     * @param tipo - Tipo de Financiamento
     * @return Lista de Valores Inteiros correspondente ao número de prestações
     */
    public static List<Integer> getListaPrestacoesByTipo(TiposFinanciamento tipo){
        if(tipo == null)
            tipo = TiposFinanciamento.EXTERNO;
        List<Integer> lista = new ArrayList<>();
        int limite = 1;
        switch (tipo) {
            case EXTERNO:
                limite = 5;
                break;
            case INTERNO:
                limite = 4;
                break;
        }
        for(int i = 1; i<= limite; i++)
            lista.add(12 * i);
        return lista;
    }

    /**
     * Obter a Descrição do Tipo de Financiamento
     */
    public static String getDescricao(Integer posicao){
        if(posicao== null)
            return TiposFinanciamento.EXTERNO.designacao;
        for(TiposFinanciamento tipo : TiposFinanciamento.values()){
            if(tipo.ordinal() == posicao)
                return tipo.designacao;
        }
        return TiposFinanciamento.EXTERNO.designacao;
    }
}

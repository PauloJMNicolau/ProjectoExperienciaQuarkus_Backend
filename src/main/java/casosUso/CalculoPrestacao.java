package casosUso;

import entidades.Erros;
import entidades.Financiamento;
import enumeradores.TiposFinanciamento;
import org.eclipse.microprofile.config.ConfigProvider;

/**
 * Classe do Caso de Uso de Cálculo de Prestações
 */
 public class CalculoPrestacao{
    Double fatorExterno = ConfigProvider.getConfig().getValue("fator.externo", Double.class);
    Double fatorInterno = ConfigProvider.getConfig().getValue("fator.interno", Double.class);
    private Financiamento financiamento;

    public CalculoPrestacao(Financiamento financiamento) {
        setFinanciamento(financiamento);
    }

    public Financiamento getFinanciamento() {
        return financiamento;
    }

    public void setFinanciamento(Financiamento financiamento) {
        this.financiamento = financiamento;
    }

    /**
     * Função de Calculo do valor da Prestação
     * @return
     */
    public Erros calculoValorPrestacao() {
        if (getFinanciamento() == null || getFinanciamento().getTipo() == null)
            return getErros();
        Double prestacao = 1d;
        switch (TiposFinanciamento.getFinanciamentoByOrdering(getFinanciamento().getTipo())) {
            case INTERNO:
                if(getFinanciamento().getMensalidades() < 12 || getFinanciamento().getMensalidades() > 48)
                    return new Erros("ERRO: Mensalidade inválida");
                prestacao = getFinanciamento().getValor() * fatorInterno
                        / getFinanciamento().getMensalidades();
                break;
            case EXTERNO:
                if(getFinanciamento().getMensalidades() < 12 || getFinanciamento().getMensalidades() > 60)
                    return new Erros("ERRO: Mensalidade inválida");
                prestacao = getFinanciamento().getValor() * fatorExterno
                        / getFinanciamento().getMensalidades();
                break;
        }
           getFinanciamento().setPrestacao(prestacao);
        return null;
    }

    /**
     * Obter os Erros nos parametros
     * @return
     */
    private Erros getErros(){
        Erros erros = new Erros("ERRO: Falta indicar parametros" );
        if(getFinanciamento() == null)
            erros.addErro("{Parametros Financiamento}");
        if(getFinanciamento().getTipo() == null)
            erros.addErro("{Tipo Financiamento}");
        return erros;
    }

}

package entidades;

import enumeradores.TiposFinanciamento;

import java.io.Serializable;

//Classe Entidade do Financiamento
public class Financiamento implements Serializable {
    private Integer mensalidades;
    private Double valor;
    private TiposFinanciamento tipo;
    private Double prestacao;
    private String nome;
    private String contacto;

    public Financiamento(Integer mensalidades, Double valor, Integer tipo){
        setTiposFinanciamento(tipo);
        setMensalidades(mensalidades);
        setValor(valor);
        setPrestacao(0d);
        setNome("");
        setContacto("");
    }

    public Integer getMensalidades() {
        return mensalidades;
    }

    public void setMensalidades(Integer mensalidades) {
        this.mensalidades = mensalidades;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getTipo() {
        return tipo.ordinal();
    }

    public void setTiposFinanciamento(Integer tipo) {
        this.tipo = TiposFinanciamento.getFinanciamentoByOrdering(tipo);
    }

    public Double getPrestacao() {
        return prestacao;
    }

    public void setPrestacao(Double prestacao) {
        this.prestacao = prestacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        String strBuffer = getNome() + ";" +
                getContacto() + ";" +
                TiposFinanciamento.getDescricao(getTipo()) + ";" +
                getMensalidades() + ";" +
                getValor() + ";" +
                getPrestacao() + ";";
        return strBuffer;
    }
}

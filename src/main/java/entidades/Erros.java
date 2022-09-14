package entidades;

import java.util.ArrayList;

/**
 * Classe para registo dos erros nos serviços / operações
 */
public class Erros {
    private String mensagem;
    private ArrayList<String> lista;

    public Erros(String mensagem) {
        this.mensagem = mensagem;
        this.lista = new ArrayList<>();
    }

    public Erros(String mensagem, ArrayList<String> lista) {
        this.mensagem = mensagem;
        this.lista = lista;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ArrayList<String> getLista() {
        return lista;
    }

    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }

    public void addErro(String erro){
        this.lista.add(erro);
    }
}

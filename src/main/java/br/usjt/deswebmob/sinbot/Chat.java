package br.usjt.deswebmob.sinbot;

/**
 * Created by supor on 30/04/2018.
 */

public class Chat {
    public String mensagem;
    public int isSend;
    public  int contador;
    public String data;

    public Chat(String mensagem, int isSend) {
        this.mensagem = mensagem;
        this.isSend = isSend;
    }

    public Chat() {
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int isSend() {
        return isSend;
    }

    public void setSend(int send) {
        isSend = send;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

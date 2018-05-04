package br.usjt.deswebmob.sinbot;

/**
 * Created by supor on 30/04/2018.
 */

public class Chat {
    public String mensagem;
    public boolean isSend;

    public Chat(String mensagem, boolean isSend) {
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

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }
}

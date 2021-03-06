package br.com.projetoPoo.model;

import java.time.LocalDate;

public class Itens {
    private Long id;
    private String titulo;
    private String local;
    private String observacao;
    private LocalDate dateTime;
    private Status status;

    public Itens() {
    }

    public Itens(Long id, String titulo, String local, String observacao, LocalDate dateTime, Status status) {
        this.id = id;
        this.titulo = titulo;
        this.local = local;
        this.observacao = observacao;
        this.dateTime = dateTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

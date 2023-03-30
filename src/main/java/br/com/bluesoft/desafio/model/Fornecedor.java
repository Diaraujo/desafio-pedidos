package br.com.bluesoft.desafio.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Fornecedor {

    @Id
    private String id;

    private String nome;

    private String cnpj;

    private List<Preco> preco;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Preco> getPreco() {
        return preco;
    }

    public void setPreco(List<Preco> preco) {
        this.preco = preco;
    }
}

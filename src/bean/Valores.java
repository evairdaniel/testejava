/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author Evair Daniel
 */
public class Valores {

    private int id;
    private Float valor_primeira_hora;
    private Float valor_demais_horas;
    private String data_fim;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getValor_primeira_hora() {
        return valor_primeira_hora;
    }

    public void setValor_primeira_hora(Float valor_primeira_hora) {
        this.valor_primeira_hora = valor_primeira_hora;
    }

    public Float getValor_demais_horas() {
        return valor_demais_horas;
    }

    public void setValor_demais_horas(Float valor_demais_horas) {
        this.valor_demais_horas = valor_demais_horas;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import java.sql.Timestamp;

/**
 *
 * @author Evair Daniel
 */
public class Movimentacao {

    private int id;
    private String placa;
    private String modelo;
    private Timestamp data_entrada;
    private Timestamp data_saida;
    private int tempo;
    private Float valor_pago;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Timestamp getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Timestamp data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Timestamp getData_saida() {
        return data_saida;
    }

    public void setData_saida(Timestamp data_saida) {
        this.data_saida = data_saida;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public Float getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(Float valor_pago) {
        this.valor_pago = valor_pago;
    }

}

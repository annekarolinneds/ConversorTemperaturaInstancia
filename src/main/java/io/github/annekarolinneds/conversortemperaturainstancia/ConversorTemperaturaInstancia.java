/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package io.github.annekarolinneds.conversortemperaturainstancia;

/**
 *
 * @author annekarolinneds
 */
public class ConversorTemperaturaInstancia {
    private double valor;
    private String unidade;

    public ConversorTemperaturaInstancia(double valor, String unidade) {
        if (unidade.equalsIgnoreCase("K") && valor < 0) {
            throw new IllegalArgumentException("Temperatura Kelvin não pode ser negativa.");
        }
        if (unidade.equalsIgnoreCase("C") && valor < -273.15) {
            throw new IllegalArgumentException("Temperatura Celsius não pode ser inferior a -273.15.");
        }
        if (unidade.equalsIgnoreCase("F") && valor < -459.67) {
            throw new IllegalArgumentException("Temperatura Fahrenheit não pode ser inferior a -459.67.");
        }
        this.valor = valor;
        this.unidade = unidade;
    }

    public double paraCelsius() {
        switch (unidade.toUpperCase()) {
            case "K":
                return kelvinParaCelsius(valor);
            case "F":
                return fahrenheitParaCelsius(valor);
            case "C":
                return valor;
            default:
                throw new IllegalArgumentException("Unidade desconhecida.");
        }
    }

    public double paraFahrenheit() {
        switch (unidade.toUpperCase()) {
            case "K":
                return kelvinParaFahrenheit(valor);
            case "C":
                return celsiusParaFahrenheit(valor);
            case "F":
                return valor;
            default:
                throw new IllegalArgumentException("Unidade desconhecida.");
        }
    }

    public double paraKelvin() {
        switch (unidade.toUpperCase()) {
            case "C":
                return celsiusParaKelvin(valor);
            case "F":
                return fahrenheitParaKelvin(valor);
            case "K":
                return valor;
            default:
                throw new IllegalArgumentException("Unidade desconhecida.");
        }
    }

    private double celsiusParaFahrenheit(double c) {
        return (c * 9/5) + 32;
    }

    private double celsiusParaKelvin(double c) {
        return c + 273.15;
    }

    private double fahrenheitParaCelsius(double f) {
        return (f - 32) * 5/9;
    }

    private double fahrenheitParaKelvin(double f) {
        return celsiusParaKelvin(fahrenheitParaCelsius(f));
    }

    private double kelvinParaCelsius(double k) {
        return k - 273.15;
    }

    private double kelvinParaFahrenheit(double k) {
        return celsiusParaFahrenheit(kelvinParaCelsius(k));
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Testes
        try {
            ConversorTemperaturaInstancia temp1 = new ConversorTemperaturaInstancia(300, "K");
            System.out.println("300K em Celsius: " + temp1.paraCelsius());
            System.out.println("300K em Fahrenheit: " + temp1.paraFahrenheit());

            ConversorTemperaturaInstancia temp2 = new ConversorTemperaturaInstancia(27, "C");
            System.out.println("27C em Kelvin: " + temp2.paraKelvin());
            System.out.println("27C em Fahrenheit: " + temp2.paraFahrenheit());

            ConversorTemperaturaInstancia temp3 = new ConversorTemperaturaInstancia(80, "F");
            System.out.println("80F em Celsius: " + temp3.paraCelsius());
            System.out.println("80F em Kelvin: " + temp3.paraKelvin());

            // Testando exceções
            ConversorTemperaturaInstancia temp4 = new ConversorTemperaturaInstancia(-500, "K");  // Deve lançar exceção
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        try {
            ConversorTemperaturaInstancia temp5 = new ConversorTemperaturaInstancia(-300, "C");  // Deve lançar exceção
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        try {
            ConversorTemperaturaInstancia temp6 = new ConversorTemperaturaInstancia(-500, "F");  // Deve lançar exceção
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}


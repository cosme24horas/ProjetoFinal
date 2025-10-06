package src;

public class Candidato2 extends Pessoa {
    private int numero;
    private String cargo;
    //private Cargo cargo;
    private int votos;

    public Candidato2(String nome, int numero, String cargo) {
        super(nome);
        this.numero = numero;
        this.cargo = cargo;
        this.votos = 0;
    }

    public int getNumero() {
        return numero;
    }

    public String getCargo() {
        return cargo;
    }

    public int getVotos() {
        return votos;
    }

    public void adicionarVoto() {
        votos++;
    }
}

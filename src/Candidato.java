package src;
public class Candidato extends Pessoa {
    private int numero;
    private Cargo cargo;
    private int votos;

    public Candidato(String nome, int numero, Cargo cargo) {
        super(nome);
        this.numero = numero;
        this.cargo = cargo;
        this.votos = 0;
    }

    public int getNumero() {
        return numero;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public int getVotos() {
        return votos;
    }

    public void adicionarVoto() {
        votos++;
    }
}
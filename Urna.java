public class Urna {
    public static void main(String[] args) {
        Candidato c1 = new Candidato("Maria", 12, Cargo.PREFEITO);
        Candidato c2 = new Candidato("João", 34, Cargo.VEREADOR);

        c1.adicionarVoto();
        c1.adicionarVoto();
        c2.adicionarVoto();

        System.out.println("Candidato: " + c1.getNome() + " | Cargo: " + c1.getCargo() + " | Votos: " + c1.getVotos());
        System.out.println("Candidato: " + c2.getNome() + " | Cargo: " + c2.getCargo() + " | Votos: " + c2.getVotos());
    }
}


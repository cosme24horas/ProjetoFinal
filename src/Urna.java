package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Urna {
    private Candidato2[] candidatosPrefeito;
    private Candidato2[] candidatosVereador;
    private String dataFormatoBrasileiro;

    public static void main(String[] args) {
        Urna urna = new Urna();
        int opcao = 0;
        
        //Inicializando a urna eletrônica
        LocalDate data = LocalDate.now();
        DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatoBrasileiro = data.format(formatoBrasileiro);
        
        //urna.inicializar();

        Scanner scanner = new Scanner(System.in);
        
        //Prepara e exibe o menu principal para o eleitor
        System.out.println("Urna Eletronica");
        System.out.println("Data: " + dataFormatoBrasileiro);
        System.out.println("1 - Votar em Prefeito \n2 - Votar em Vereador");
        opcao = scanner.nextInt();

        if(opcao == 1){
            System.out.println("Candidatos a Prefeito");
            for(Candidato2 candidato : urna.getcandidatosPrefeito()){
                System.out.println(candidato.getNumero() + " - " + candidato.getNome());
            }
        } else if(opcao == 2){
            System.out.println("Candidatos a Vereador");
            for(Candidato2 candidato :urna.getcandidatosVereador()){
                System.out.println(candidato.getNumero() + " - " + candidato.getNome());
            }
        } else {
            System.out.println("Opcao invalida!");
        }

        System.out.println("Digite o numero do candidato");
        //System.out.println(candidatoA.getNumeroCandidato() + " - " + candidatoA.getNome());
        //System.out.println(candidatoB.getNumeroCandidato() + " - " + candidatoB.getNome());
        System.out.println("0 - Branco");
        System.out.println("-1 - Encerrar votacao");

        // c1.adicionarVoto();
        // c1.adicionarVoto();
        // c2.adicionarVoto();

        // System.out.println("Candidato: " + c1.getNome() + " | Cargo: " + c1.getCargo() + " | Votos: " + c1.getVotos());
        // System.out.println("Candidato: " + c2.getNome() + " | Cargo: " + c2.getCargo() + " | Votos: " + c2.getVotos());

        scanner.close();
    }
    
    public Urna() {
 
        //Candidato2[] candidatosPrefeito, candidatosVereador;

        //Mock para candidato
        //Candidato c1 = new Candidato("Maria", 12, Cargo.PREFEITO);
        //Candidato c2 = new Candidato("João", 34, Cargo.VEREADOR);
        Candidato2 c1 = new Candidato2("Maria", 10, "PREFEITO");
        Candidato2 c2 = new Candidato2("Jose", 11, "VEREADOR");
        
        //Montando a lista de candidatos para prefeito
        //candidatosPrefeito = new Candidato[1];
        this.candidatosPrefeito = new Candidato2[1];
        this.candidatosPrefeito[0] = c1;

        //Montando a lista de candidatos para vereador
        //candidatosPrefeito = new Candidato[1];
        this.candidatosVereador = new Candidato2[1];
        this.candidatosVereador[0] = c2;
    }
    public Candidato2 [] getCandidatosPrefeito() {
        return candidatosPrefeito;
    }
    public Candidato2 [] getCandidatosVereador() {
        return candidatosVereador;
    }
}


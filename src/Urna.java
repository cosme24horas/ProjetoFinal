package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Urna {
    private Candidato[] candidatosPrefeito;
    private Candidato[] candidatosVereador;
    private String dataFormatoBrasileiro;

    public static void main(String[] args) {
        Urna urnaSerraSaudadeMG = new Urna();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        
        // Criando candidatos a prefeito
        Candidato[] prefeitos = {
            new Candidato("DERLI DONIZETE - AVANTE", 70, Cargo.PREFEITO),
            new Candidato("NEUSA RIBEIRO - PP", 11, Cargo.PREFEITO)            
        };
        
        // Criando candidatos a vereador
        Candidato[] vereadores = {
            new Candidato("JADILSON SILVA FILHO ZE MELOSO - PP", 11222, Cargo.VEREADOR),
            new Candidato("TIAGO ALVES - PODE", 20000, Cargo.VEREADOR),
            new Candidato("CARLINHO DA TEREZA - PODE", 20111, Cargo.VEREADOR),
            new Candidato("SILVIA LOPES - PP", 11555, Cargo.VEREADOR)
        };
        
        // Este método zera tudo preparando a urna para uma nova votação.
        // A lista de candidatos deve ser carregada neste momento.
        urnaSerraSaudadeMG.inicializarVotacao(prefeitos, vereadores);
        
        //Prepara e exibe o menu principal para o eleitor
        System.out.println("Urna Eletronica");
        System.out.println("Data: " + urnaSerraSaudadeMG.dataFormatoBrasileiro);
        System.out.println("1 - Votar em Prefeito \n2 - Votar em Vereador");
        opcao = scanner.nextInt();

        if(opcao == 1){
            System.out.println("Candidatos a Prefeito");
            urnaSerraSaudadeMG.exibeCandidatosPrefeito();
        } else if(opcao == 2){
            System.out.println("Candidatos a Vereador");
            urnaSerraSaudadeMG.exibeCandidatosVereador();
        } else {
            System.out.println("Opcao invalida!");
        }
        System.out.println("Digite o numero do candidato");
        
        System.out.println("0 - Branco");
        System.out.println("-1 - Encerrar votacao");
        //urnaSerraSaudadeMG.Votar(scanner.nextInt(), opcao);
        //urnaSerraSaudadeMG.confirmaVoto();
        //urnaSerraSaudadeMG.mostraApuracao();

        scanner.close();
    }
    
    public Urna() {
        // Inicializando a urna eletrônica com a data atual
        LocalDate data = LocalDate.now();
        DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataFormatoBrasileiro = data.format(formatoBrasileiro);
    }    

     // Este método zera tudo preparando a urna para uma nova votação.
     // A lista de candidatos deve ser carregada neste momento.
    public void inicializarVotacao(Candidato[] prefeitos, Candidato[] vereadores) {
        this.candidatosPrefeito = prefeitos;
        this.candidatosVereador = vereadores;
        System.out.println("Urna inicializada com " + prefeitos.length + " candidatos a prefeito e " + vereadores.length + " candidatos a vereador.");
    }    

    // Exibe a lista de candidatos a prefeito
    public void exibeCandidatosPrefeito() {
        if (candidatosPrefeito != null) {
            for (Candidato candidato : candidatosPrefeito) {
                System.out.println(candidato.getNumero() + " - " + candidato.getNome());
            }
        }
    }
    
    // Exibe a lista de candidatos a vereador
    public void exibeCandidatosVereador() {
        if (candidatosVereador != null) {
            for (Candidato candidato : candidatosVereador) {
                System.out.println(candidato.getNumero() + " - " + candidato.getNome());
            }
        }
    }
    
    public Candidato[] getCandidatosPrefeito() {
        return candidatosPrefeito;
    }
    
    public Candidato[] getCandidatosVereador() {
        return candidatosVereador;
    }
}
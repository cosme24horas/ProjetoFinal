package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Urna implements Apuravel {
    private Candidato[] candidatosPrefeito;
    private Candidato[] candidatosVereador;
    private String dataFormatoBrasileiro;
    
    // Contadores para votos brancos e nulos por cargo
    private int votosBrancosPrefeito;
    private int votosNulosPrefeito;
    private int votosBrancosVereador;
    private int votosNulosVereador;

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
        
        // Loop principal da votação - continua até alguém digitar -1
        boolean votacaoAtiva = true;
        
        while (votacaoAtiva) {
            //Prepara e exibe o menu principal para o eleitor
            System.out.println("\n" + "=".repeat(60));
            System.out.println("                    URNA ELETRÔNICA");
            System.out.println("Data: " + urnaSerraSaudadeMG.dataFormatoBrasileiro);
            System.out.println("=".repeat(60));

            System.out.println("1 - Votar em Prefeito"); 
            System.out.println("2 - Votar em Vereador");
            System.out.println("-1 - Encerrar votação e mostrar apuração");
            System.out.println("Digite sua opção: ");
            opcao = scanner.nextInt();

            if(opcao == 1){
                votacaoAtiva = urnaSerraSaudadeMG.processarVotacao(Cargo.PREFEITO, scanner);
            } else if(opcao == 2){
                votacaoAtiva = urnaSerraSaudadeMG.processarVotacao(Cargo.VEREADOR, scanner);
            } else if(opcao == -1){
                System.out.println("Encerrando votação...");
                votacaoAtiva = false; // Encerra o loop principal
            } else { 
                System.out.println("Opção inválida! Tente novamente.");
            }
        }

        // Mostrar apuração final dos votos
        System.out.println("\n" + "VOTAÇÃO ENCERRADA!");
        System.out.println("Iniciando apuração...\n");
        urnaSerraSaudadeMG.apurar();
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
        
        // Zerando os contadores de votos brancos e nulos
        this.votosBrancosPrefeito = 0;
        this.votosNulosPrefeito = 0;
        this.votosBrancosVereador = 0;
        this.votosNulosVereador = 0;
        
        System.out.println("Urna inicializada com " + prefeitos.length + " candidatos a prefeito e " + vereadores.length + " candidatos a vereador.");
        System.out.println("Candidatos a Prefeito: ");
        this.exibeCandidatosPrefeito();
        this.exibeSeparador();
        System.out.println("Candidatos a Vereador: ");
        this.exibeCandidatosVereador();
        this.exibeSeparador();
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

    public void exibeSeparador() {
        System.out.println("<-------------------------------------------------->");
    }

    public void exibeInstrucoes() {
        System.out.println("Digite o numero do candidato");            
        System.out.println("0 - Branco");
        System.out.println("-1 - Encerrar votacao");
    }
    
    
    //Processa uma votação completa com confirmação     
    public boolean processarVotacao(Cargo cargo, Scanner scanner) {
        boolean votoConfirmado = false;
        
        while (!votoConfirmado) {            
            exibeInstrucoes();
            System.out.println(" " + cargo);           
            int numeroCandidato = scanner.nextInt();           
            
            if (numeroCandidato == -1) {
                if (confirmaVoto(numeroCandidato, cargo, scanner)) {
                    System.out.println("Solicitação de encerramento confirmada!");
                    return false; 
                }
                continue;
            }            
            
            if (confirmaVoto(numeroCandidato, cargo, scanner)) {
                // Voto confirmado, processar
                recebeVoto(numeroCandidato, cargo);
                votoConfirmado = true;
                System.out.println("Voto registrado com sucesso!");
                System.out.println("Próximo eleitor pode votar...");
            }           
        }
        
        return true;
    }
    
    
     //Recebe o voto do eleitor e processa de acordo com o cargo     
    public void recebeVoto(int numeroCandidato, Cargo cargo) {        
        if (numeroCandidato == -1) {
            return;
        }
        
        if (numeroCandidato == 0) {
            if (cargo == Cargo.PREFEITO) {
                votosBrancosPrefeito++;
                System.out.println("Voto BRANCO para PREFEITO computado!");
            } else if (cargo == Cargo.VEREADOR) {
                votosBrancosVereador++;
                System.out.println("Voto BRANCO para VEREADOR computado!");
            }
            return;
        }        
        
        Candidato[] listaCandidatos = (cargo == Cargo.PREFEITO) ? candidatosPrefeito : candidatosVereador;
        Candidato candidatoEncontrado = null;
        
        for (Candidato candidato : listaCandidatos) {
            if (candidato.getNumero() == numeroCandidato) {
                candidatoEncontrado = candidato;
                break;
            }
        }
        
        // Processar o voto
        if (candidatoEncontrado != null) {
            candidatoEncontrado.adicionarVoto();
            System.out.println("Voto computado para: " + candidatoEncontrado.getNome());
            System.out.println("Número: " + candidatoEncontrado.getNumero());
            System.out.println("Cargo: " + candidatoEncontrado.getCargo());
        } else {
            // Voto nulo - candidato não encontrado
            if (cargo == Cargo.PREFEITO) {
                votosNulosPrefeito++;
                System.out.println("VOTO NULO para PREFEITO - Candidato não encontrado!");
            } else if (cargo == Cargo.VEREADOR) {
                votosNulosVereador++;
                System.out.println("VOTO NULO para VEREADOR - Candidato não encontrado!");
            }
        }
    }    

    // Confirma o voto com o eleitor antes de registrar definitivamente
    private boolean confirmaVoto(int numeroCandidato, Cargo cargo, Scanner scanner) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           CONFIRMAÇÃO DO VOTO");
        System.out.println("=".repeat(50));
        
        // Mostrar informações do voto
        if (numeroCandidato == 0) {
            System.out.println("SEU VOTO: BRANCO para " + cargo);
        } else if (numeroCandidato == -1) {
            System.out.println("AÇÃO: ENCERRAR VOTAÇÃO");
        } else {
            // Buscar candidato para mostrar informações
            Candidato[] listaCandidatos = (cargo == Cargo.PREFEITO) ? candidatosPrefeito : candidatosVereador;
            Candidato candidatoEncontrado = null;
            
            for (Candidato candidato : listaCandidatos) {
                if (candidato.getNumero() == numeroCandidato) {
                    candidatoEncontrado = candidato;
                    break;
                }
            }
            
            if (candidatoEncontrado != null) {
                System.out.println("SEU VOTO:");
                System.out.println("Número: " + candidatoEncontrado.getNumero());
                System.out.println("Nome: " + candidatoEncontrado.getNome());
                System.out.println("Cargo: " + candidatoEncontrado.getCargo());
            } else {
                System.out.println("SEU VOTO: NULO (candidato não encontrado)");
                System.out.println("Número digitado: " + numeroCandidato);
                System.out.println("Cargo: " + cargo);
            }
        }
        
        System.out.println("\n1 - CONFIRMAR voto");
        System.out.println("2 - CORRIGIR voto");
        System.out.print("Escolha uma opção: ");
        
        int opcao = scanner.nextInt();
        
        if (opcao == 1) {
            System.out.println("VOTO CONFIRMADO!");
            return true;
        } else {
            System.out.println("VOTO CANCELADO - Digite novamente");
            return false;
        }
    }
    

     //Realiza a apuração completa com cálculo de percentuais válidos e totais
    public void apurar() {
        System.out.println("=".repeat(60));
        System.out.println("            APURAÇÃO DOS VOTOS");
        System.out.println("=".repeat(60));        
        
        System.out.println("\n PREFEITO:");
        System.out.println("-".repeat(60));
        
        // Calcular totais para prefeito
        int prefeitosValidos = 0;
        for (Candidato candidato : candidatosPrefeito) {
            prefeitosValidos += candidato.getVotos();
        }
        int prefeitosTotal = prefeitosValidos + votosBrancosPrefeito + votosNulosPrefeito;
        
        // Exibir candidatos a prefeito
        for (Candidato candidato : candidatosPrefeito) {
            System.out.println(criaItem(candidato, prefeitosValidos, prefeitosTotal));
        }
        
        // Exibir brancos e nulos para prefeito
        if (votosBrancosPrefeito > 0) {
            System.out.println(criaItemEspecial("BRANCOS", votosBrancosPrefeito, prefeitosTotal));
        }
        if (votosNulosPrefeito > 0) {
            System.out.println(criaItemEspecial("NULOS", votosNulosPrefeito, prefeitosTotal));
        }
        
        // Totais prefeito
        System.out.println("-".repeat(60));
        System.out.printf("TOTAL VÁLIDOS: %d votos | TOTAL GERAL: %d votos%n", 
                         prefeitosValidos, prefeitosTotal);
        
        // APURAÇÃO VEREADOR
        System.out.println("\n VEREADOR:");
        System.out.println("-".repeat(60));
        
        // Calcular totais para vereador
        int vereadoresValidos = 0;
        for (Candidato candidato : candidatosVereador) {
            vereadoresValidos += candidato.getVotos();
        }
        int vereadoresTotal = vereadoresValidos + votosBrancosVereador + votosNulosVereador;
        
        // Exibir candidatos a vereador
        for (Candidato candidato : candidatosVereador) {
            System.out.println(criaItem(candidato, vereadoresValidos, vereadoresTotal));
        }
        
        // Exibir brancos e nulos para vereador
        if (votosBrancosVereador > 0) {
            System.out.println(criaItemEspecial("BRANCOS", votosBrancosVereador, vereadoresTotal));
        }
        if (votosNulosVereador > 0) {
            System.out.println(criaItemEspecial("NULOS", votosNulosVereador, vereadoresTotal));
        }
        
        // Totais vereador
        System.out.println("-".repeat(60));
        System.out.printf("TOTAL VÁLIDOS: %d votos | TOTAL GERAL: %d votos%n", 
                         vereadoresValidos, vereadoresTotal);
        
        // RESUMO GERAL
        int totalGeralVotos = prefeitosTotal + vereadoresTotal;
        System.out.println("\n" + "=".repeat(60));
        System.out.printf("            RESUMO GERAL: %d votos computados%n", totalGeralVotos);
        System.out.println("=".repeat(60));
    }
    
  
    //Cria um Item para apuração com cálculo dos percentuais válidos e totais
    private Item criaItem(Candidato c, int validos, int total) {
        double pValidos = validos == 0 ? 0.0 : (100.0 * c.getVotos() / validos);
        double pTotal = total == 0 ? 0.0 : (100.0 * c.getVotos() / total);
        return new Item(c.getNome(), c.getNumero(), c.getVotos(), pValidos, pTotal);
    }
    
    
     //Cria um Item para votos brancos ou nulos (sem número de candidato)
    private Item criaItemEspecial(String tipo, int votos, int total) {
        double pTotal = total == 0 ? 0.0 : (100.0 * votos / total);
        return new Item(tipo, 0, votos, 0.0, pTotal);
    }
        
    public int getVotosBrancosPrefeito() {
        return votosBrancosPrefeito;
    }
    
    public int getVotosNulosPrefeito() {
        return votosNulosPrefeito;
    }
    
    public int getVotosBrancosVereador() {
        return votosBrancosVereador;
    }
    
    public int getVotosNulosVereador() {
        return votosNulosVereador;
    }
    
    public Candidato[] getCandidatosPrefeito() {
        return candidatosPrefeito;
    }
    
    public Candidato[] getCandidatosVereador() {
        return candidatosVereador;
    }
}
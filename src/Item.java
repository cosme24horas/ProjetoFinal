package src;

/*
  Record Item para resultado da apuração
  Representa um item da apuração com nome, número, votos e percentuais
 */
public record Item(String nome, int numero, int votos, double percValidos, double percTotal) {
    
    /*
      Formatação personalizada para exibição dos resultados
      Formato: "Nº número | nome | votos | percentual% válidos | percentual% total"
     */    
    public String toString() {
        return "Nº %-6d | %-18s | %5d | %8.2f%% válidos | %6.2f%% total"
                .formatted(numero, nome, votos, percValidos, percTotal);
    }
}
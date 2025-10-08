package src;

/*
  Interface Apuravel
  Define o contrato para classes que podem realizar apuração de votos
*/
public interface Apuravel {
    
    /*
      Método para realizar a apuração dos votos
      Implementações devem calcular e exibir os resultados da votação
      incluindo percentuais válidos e totais
    */
    void apurar();
}
// Define o pacote principal da sua aplicação.
// O Spring Boot, por padrão, escaneia todos os subpacotes a partir deste em busca de componentes (@Controller, @Service, @Repository, etc.).
package com.exemplo.crudmongo;

// Importações de classes do Spring Boot.
import org.springframework.boot.SpringApplication; // Classe principal que o Spring usa para iniciar a aplicação.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Anotação que habilita várias configurações automáticas do Spring Boot.

/**
 * A anotação @SpringBootApplication é uma anotação de conveniência que adiciona:
 * 1. @Configuration: Marca a classe como uma fonte de definições de beans para o contexto da aplicação.
 * 2. @EnableAutoConfiguration: Diz ao Spring Boot para começar a adicionar beans com base nas configurações do classpath, outros beans e várias propriedades. Por exemplo, se ele vê a dependência do "Spring Web", ele configura um servidor web Tomcat.
 * 3. @ComponentScan: Diz ao Spring para procurar por outros componentes, configurações e serviços no pacote 'com.exemplo.crudmongo', permitindo que ele encontre e registre os seus controllers, services e repositories.
 */
@SpringBootApplication
public class CrudMongoApplication {

    /**
     * Este é o método 'main', o ponto de entrada padrão para qualquer aplicação Java.
     * Quando você executa a aplicação, a JVM (Java Virtual Machine) chama este método primeiro.
     * @param args Argumentos de linha de comando que podem ser passados ao iniciar a aplicação (não estamos usando nenhum neste projeto).
     */
    public static void main(String[] args) {
        // Esta linha estática é o que efetivamente inicia a aplicação Spring Boot.
        // SpringApplication.run() faz todo o trabalho pesado:
        // 1. Cria o contexto da aplicação.
        // 2. Realiza o escaneamento de classes (component scan).
        // 3. Inicia o servidor web embutido (Tomcat, por padrão).
        // 4. E muito mais...
        SpringApplication.run(CrudMongoApplication.class, args);
    }
}
package com.example;

import com.example.domain.entity.Cliente;
import com.example.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoPersist {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando Clientes");
            Cliente cliente = new Cliente("Danilo");
            clientes.salvar(cliente);

            Cliente cliente2 = new Cliente("Patrick");
            clientes.salvar(cliente2);

            System.out.println("Lendo Clientes");
            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando Clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.atualizar(c);
            });

            System.out.println("Buscando Clientes");
            clientes.buscarPorNome("Dan").forEach(System.out::println);


            clientes.obterTodos().forEach(c -> {
                clientes.deletar(c);
            });

            System.out.println("Lendo Clientes");
            todosClientes = clientes.obterTodos();
            if (todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            }else{
                todosClientes.forEach(System.out::println);
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoPersist.class, args);
    }
}

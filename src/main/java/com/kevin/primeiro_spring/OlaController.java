package com.kevin.primeiro_spring;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// a caxinha de ferramenta do springboot, que vai permitir que a gente crie uma API REST
class SenhaResponse {
    public String senha;
    public  String forca;
    public  int tamanho;
//construtor
    public SenhaResponse(String senha, int tamanho, String forca) {
        this.senha = senha;
        this.forca = forca;
        this.tamanho = tamanho; 
    }
}

@RestController
public class OlaController {

    @GetMapping("/gerar-senha")
    public SenhaResponse gerarSenha(
        @RequestParam(value = "tamanho", defaultValue = "8") int tamanho,
        @RequestParam(value = "numero", defaultValue = "true") boolean numero,
        @RequestParam(value = "maiuscula", defaultValue = "true") boolean maiuscula,
        @RequestParam(value = "minuscula", defaultValue = "true") boolean minuscula,
        @RequestParam(value = "simbolos", defaultValue = "true") boolean simbolos
    ) { 

        // Validações de tamanho (Seus seguranças funcionando!)
        if (tamanho < 8) {
            return new SenhaResponse("Erro: O tamanho minimo da senha devera ser de 8 caracteres", tamanho, "Fraca");
        }
        if (tamanho > 50) { 
            return new SenhaResponse("Erro: O tamanho maximo da senha devera ser de 50 caracteres", tamanho, "Fraca");
        }

        // Começamos com a lista de opções vazia
        String caracteresPermissoes = "";

        // Se o usuário quiser maiúsculas, adicionamos na lista
        if (maiuscula) {
            caracteresPermissoes += "ABCDEFGHIJKLMNOPQRSTUVWXZY";
        }
        
        // Se o usuário quiser minúsculas, adicionamos na lista
        if (minuscula) {
            caracteresPermissoes += "abcdefghijklmnopqrstuvwxyz";
        }

        // Se o usuário quiser números, adicionamos na lista
        if (numero) {
            caracteresPermissoes += "0123456789";
        }

        // Se o usuário quiser símbolos, adicionamos na lista
        if (simbolos) {
            caracteresPermissoes += "!@#$%^&*()_+";
        }

        // Segurança Extra: E se o usuário colocar FALSE em tudo? A lista fica vazia!
        if (caracteresPermissoes.isEmpty()) {
            return new SenhaResponse("Erro: Voce precisa escolher pelo menos um tipo de caractere para a senha!", tamanho, "Fraca");
        }

        // O seu loop perfeito para sortear a senha
        StringBuilder senha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < tamanho; i++) {
            int index = random.nextInt(caracteresPermissoes.length());
            senha.append(caracteresPermissoes.charAt(index));
        }
        //==============================================================================
        // 🚀SISTEMA DE PONTUAÇÃO DE SEGURANÇA (O medidor de força!)
        //==============================================================================
        int pontosForte = 0;
        
        // Se o tamanho da senha for maior ou igual a 10, adicionamos 2 pontos 
        if (tamanho >= 10) {
            pontosForte += 2;
        } else if (tamanho >= 8) {
            pontosForte += 1;

        }
        // Regra 2: tem numero ativo?
        if (numero) {
            pontosForte += 2;
        } else {
            pontosForte -= 1;
        }
        // Regra 3; tem simbolos ativos?
        if (simbolos) {
            pontosForte += 2;
        } else {
            pontosForte -= 1;
        }
        // vamos classifica a senha de acordo com a pontuaçao imposta 
        String ForcaSenha = "";
        if (pontosForte <= 2) {
            ForcaSenha = "Fraca";
        } else if (pontosForte <= 4) {
            ForcaSenha = "Media";
        } else if (pontosForte <= 6) {
            ForcaSenha = "Forte";
        } else if (pontosForte <= 8) { 
            ForcaSenha = "Muito Forte";
        }
        
        return new SenhaResponse(senha.toString(), tamanho, ForcaSenha);
    }
}

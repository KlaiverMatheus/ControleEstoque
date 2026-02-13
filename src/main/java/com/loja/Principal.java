package com.loja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        System.out.println("--- SISTEMA DE CONTROL DE ESTOQUE ---");
        
        while(true) {
            System.out.println("\nO que voce quer fazer?");
            System.out.println("1 - Repor Estoque");
            System.out.println("2 - Dar Baixa");
            System.out.println("3 - Sair");
            System.out.print("Digite a opcao: ");
            System.out.println("---------------------------");
            int opcao = leitor.nextInt();
            
            if (opcao == 3) {
                System.out.println("fechado");
                break;
            }

            System.out.print("Digita o ID do produto: ");
            int idProd = leitor.nextInt();
            
            System.out.print("Qual a quntidade? "); 
            int qtd = leitor.nextInt();
            
         
            String sql = "";
            if (opcao == 1) {
               
                sql = "UPDATE estoque SET quantidade = quantidade + ? WHERE produto_id = ?";
            } else if (opcao == 2) {
              
                sql = "UPDATE estoque SET quantidade = quantidade - ? WHERE produto_id = ?";
            } else {
                System.out.println("Opcao invalida, tenta de novo");
                continue; 
            }

            try {
                Connection con = Conexao.conectar();
                PreparedStatement stmt = con.prepareStatement(sql);
                
              
                stmt.setInt(1, qtd);
                stmt.setInt(2, idProd);
                
                int linhasAfetadas = stmt.executeUpdate();
                
                if(linhasAfetadas > 0) {
                    System.out.println("Estoque atualizado com suceso.");
                } else {
                    System.out.println("nao encontrado esse produto ID " + idProd + " no banco.");
                }
                
                stmt.close();
                con.close();
                
            } catch (Exception e) {
                System.out.println("Erro no banco: " + e.getMessage());
            }
        }
    }
}
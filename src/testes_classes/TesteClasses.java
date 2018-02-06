/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes_classes;

import arquivo.Arquivos;

/**
 *
 * @author Andre
 */
class TesteClasses {

    public static void main(String[] args) {
        testePegaEstensaoArquivos();
        testeCopiarArquivo();
    }

    private static void testePegaEstensaoArquivos() {
        System.out.println("Teste pega Estensão arquivo");
        for (String extensao : Arquivos.EXTENSOES_FOTOS) {

            System.out.println(extensao + ",");

        }
        
        System.out.println("---------------------\n");


        for (String extensao : Arquivos.EXTENSOES_COMPACTADOS) {

            System.out.println(extensao + ",");

        }
        
         System.out.println("---------------------\n");

        for (String extensao : Arquivos.EXTENSOES_TEXTOS) {

            System.out.println(extensao + ",");

        }
        
         System.out.println("---------------------\n");
         
        for (String extensao : Arquivos.EXTENSOES_VIDEOS) {

            System.out.println(extensao + ",");

        }
        
        System.out.println("---------------------\n");
         
        for (String extensao : Arquivos.EXTENSOES_MUSICAS) {

            System.out.println(extensao + ",");

        }


        System.out.println("----------------------------------\n");
    }
    
    private static void testeCopiarArquivo(){
        
        System.out.println("Teste pega copiar arquivos");
        
        System.out.println("Iniciar a copiar\n");
        
        
        
        Arquivos.copiarArquivoTipoFotos(new Arquivos("C:\\Users\\andre"), new Arquivos("C:\\Destino"));
        System.out.println(".........................");
        
        System.out.println("\nfinal");
        
        
        System.out.println("\nTeste concluido com sucesso!");
        
    }

}

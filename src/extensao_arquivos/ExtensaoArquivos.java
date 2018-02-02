/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extensao_arquivos;


import java.util.ArrayList;
import java.util.Arrays;



/**
 *
 * @author Andre
 */
public class ExtensaoArquivos {
    
    

    public ArrayList<String> getExtensoesFotos() {
        return extensoesFotos;
    }

    public void setExtensoesFotos(ArrayList<String> extensoesFotos) {
        this.extensoesFotos = extensoesFotos;
    }

    public ArrayList<String> getExtensoesMusica() {
        return extensoesMusica;
    }

    public void setExtensoesMusica(ArrayList<String> extensoesMusica) {
        this.extensoesMusica = extensoesMusica;
    }

    public ArrayList<String> getExtensoesVideos() {
        return extensoesVideos;
    }

    public void setExtensoesVideos(ArrayList<String> extensoesVideos) {
        this.extensoesVideos = extensoesVideos;
    }

    public ArrayList<String> getExtensoesTexto() {
        return extensoesTexto;
    }

    public void setExtensoesTexto(ArrayList<String> extensoesTexto) {
        this.extensoesTexto = extensoesTexto;
    }

    public ArrayList<String> getExtensoesCompactado() {
        return extensoesCompactado;
    }

    public void setExtensoesCompactado(ArrayList<String> extensoesCompactado) {
        this.extensoesCompactado = extensoesCompactado;
    }
    
    
    private ArrayList<String> extensoesMusica;
    private ArrayList<String> extensoesVideos;
    private ArrayList<String> extensoesFotos;
    private ArrayList<String> extensoesTexto;
    private ArrayList<String> extensoesCompactado;
    
    public ExtensaoArquivos(){
        preencherExtencoes();
    }

    
    private void preencherExtencoes() {
        ArrayList<String> extensaoFotos = new ArrayList<>();
        ArrayList<String> extensaoMusica = new ArrayList<>();
        ArrayList<String> extensaoVideos = new ArrayList<>();
        ArrayList<String> extensaoTexto = new ArrayList<>();
       ArrayList<String> extensaoCompactado = new ArrayList<>();
        
        extensaoFotos.addAll(Arrays.asList(".png", ".jpg", ".JPEG", ".GIF"));
        extensaoMusica.addAll(Arrays.asList(".mp3", ".opus"));
        extensaoVideos.addAll(Arrays.asList(".mp4",".WMA",".avi"));
        extensaoTexto.addAll(Arrays.asList(".txt", ".doc",".PDF"));
        extensaoCompactado.addAll(Arrays.asList(".ZIP", ".RAR",".7z"));
        
        setExtensoesFotos(extensaoFotos);
        setExtensoesMusica(extensaoMusica);
        setExtensoesVideos(extensaoVideos);
        setExtensoesTexto(extensaoTexto);
        setExtensoesCompactado(extensaoCompactado);
        
    }

}

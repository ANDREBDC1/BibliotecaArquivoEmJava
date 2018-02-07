/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Andre
 */
public class Arquivos extends File {

    private static File file;
    private static byte[] Arquivo;
    private static int totalDeArquivos;
    public static final String[] EXTENSOES_FOTOS = {".png", ".jpg", ".JPEG", ".GIF"};
    public static final String[] EXTENSOES_MUSICAS = {".mp3", ".opus"};
    public static final String[] EXTENSOES_TEXTOS = {".txt", ".doc", ".PDF"};
    public static final String[] EXTENSOES_VIDEOS = {".mp4", ".WMA", ".avi"};
    public static final String[] EXTENSOES_COMPACTADOS = {".ZIP", ".RAR", ".7z"};
    private static ArrayList<File>  filesIginorados;

    public static ArrayList<File> getFilesIginorados() {
        return filesIginorados;
    }

    private static void setFilesIginorados(ArrayList<File> filesIginorados) {
        Arquivos.filesIginorados = filesIginorados;
    }

    

    private static void setTotalDeArquivos(int totalDeArquivos) {
        Arquivos.totalDeArquivos = totalDeArquivos;
    }

    public static int getTotalDeArquivos() {
        return totalDeArquivos;
    }

    public static byte[] getArquivo() {
        return Arquivo;
    }

    public static void setArquivo(byte[] Arquivo) {
        Arquivos.Arquivo = Arquivo;
    }

    public static File getFile() {
        return file;
    }

    public static void setFile(File file) {
        Arquivos.file = file;
    }

    public static byte[] coverterArquivoEmArrayDeByte(File file) {
        FileInputStream fis;
        int tamanho = (int) file.length();;
        byte[] conteudoByte = new byte[tamanho];

        try {

            fis = new FileInputStream(file);
            fis.read(conteudoByte);
            fis.close();

        } catch (IOException ex) {
            throw new RuntimeException("Arquivo não encontrado!\n" + ex.getMessage());
        }

        return conteudoByte;
    }

    public static boolean deletarArquivo(String caminho) {
        File deletar = new File(caminho);
        return deletar.delete();

    }

    public static boolean deletarArquivo(File file) {
        if (file == null) {
            return false;
        }
        File deletar = file;
        return deletar.delete();

    }

    public static void moverArquivosDeUmDiretorioEspecifico(String caminhoOrigem, String caminhoDestino) {
        try {

            if (caminhoDestino.trim().isEmpty() || caminhoOrigem.trim().isEmpty()) {
                throw new IOException("Caminho de Origem e destino não pode ser vasio!");
            }

            File src = new File(caminhoOrigem);

            if (!src.exists()) {
                return;
            }

            File[] files = src.listFiles();

            if (files == null || (files.length <= 0)) {
                return;
            }

            for (File f : files) {

                String fileName = f.getName();
                boolean isArquivoSalvo = salvaArquivo(caminhoDestino, fileName, coverterArquivoEmArrayDeByte(f));
                if (isArquivoSalvo) {
                    deletarArquivo(f);
                }

            }

        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static boolean salvaArquivo(String caminhoDestino, String nomeDoArquivo, byte[] arquivo) {

        if (caminhoDestino.trim().isEmpty() || nomeDoArquivo.trim().isEmpty() || arquivo == null) {
            return false;
        }

        File criarPasta = new File(caminhoDestino);
        FileOutputStream fos;

        try {
            if (!criarPasta.exists()) { // testa se a pasta ja existe 
                criarPasta.mkdir(); // criar pasta caso nao existe
            }

            fos = new FileOutputStream(caminhoDestino + "/" + nomeDoArquivo);
            fos.write(arquivo);
            return true;

        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
    }

    public static ArrayList<File> buscaArquivos(File file, String extensao) {
        try {
            int tamMax = 99999999;
            if (extensao.trim().isEmpty()) {
                throw new RuntimeException("Extensão não Informada!");
            }
            ArrayList<File> arrayFile = new ArrayList<>();

            ArrayList<File> files;

            for (File f : file.listFiles()) {

                if (f.isDirectory() && !f.isHidden()) {
                    files = buscaArquivosSubPasta(f, extensao.toLowerCase());
                    if (!files.isEmpty() && !file.isHidden()) {
                        arrayFile.addAll(files);
                    }

                } else if (f.getName().toLowerCase().endsWith(extensao.toLowerCase())) {
                    if(f.length() > tamMax){
                       filesIginorados.add(f);
                    }else{
                        arrayFile.add(f);
                    }
                    
                }
            }

            setTotalDeArquivos(arrayFile.size() + filesIginorados.size());
            return arrayFile;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private static ArrayList<File> buscaArquivosSubPasta(File file, String extensao) {

        ArrayList<File> arrayFile = new ArrayList<>();

        try {
            if (file == null || file.isHidden()) {
                return arrayFile;
            }
            for (File ff : file.listFiles()) {

                // verificar se file e um diretorio e se não e um arquivo ocuto
                if (ff.isDirectory() && !file.isHidden()) {
                    arrayFile.addAll(buscaArquivosSubPasta(ff, extensao));
                } else if (ff.getName().toLowerCase().endsWith(extensao.toLowerCase())) {
                    arrayFile.add(ff);
                }

            }

        } finally {
            return arrayFile;
        }
    }

    public static void moverArquivos(File fileBuscar, File fileDestino, String extensao) {
        try {
            ArrayList<File> arquivos = buscaArquivos(fileBuscar, extensao);

            if (arquivos.size() <= 0) {
                return;
            }

            for (File f : arquivos) {
                if (salvaArquivo(fileDestino.getPath(), f.getName(), coverterArquivoEmArrayDeByte(f))) {
                    deletarArquivo(f);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public static void copiarArquivos(File fileBuscar, File fileDestino, String extensao) {
        try {

            ArrayList<File> arquivos = buscaArquivos(fileBuscar, extensao);

            if (arquivos.size() <= 0) {
                return;
            }

            arquivos.stream().forEach((f) -> {
                salvaArquivo(fileDestino.getPath(), f.getName(), coverterArquivoEmArrayDeByte(f));
            });

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public static void copiarArquivoTipoTexto(File diretorioDeBusca, File diretorioDestino) {

        for (String ex : EXTENSOES_TEXTOS) {
            copiarArquivos(diretorioDeBusca, diretorioDestino, ex);
        }
    }

    public static void moverArquivoTipoTexto(File diretorioDeBusca, File diretorioDestino) {
        for (String ex : EXTENSOES_TEXTOS) {
            moverArquivos(diretorioDeBusca, diretorioDestino, ex);
        }
    }

    public static void copiarArquivoTipoFotos(File diretorioDeBusca, File diretorioDestino) {
        for (String ex : EXTENSOES_FOTOS) {
            copiarArquivos(diretorioDeBusca, diretorioDestino, ex);
        }
    }

    public static void copiarArquivoTipoMusica(File diretorioDeBusca, File diretorioDestino) {
        for (String ex : EXTENSOES_MUSICAS) {
            copiarArquivos(diretorioDeBusca, diretorioDestino, ex);
        }
    }

    public static void copiarArquivoTipoVideo(File diretorioDeBusca, File diretorioDestino) {
        for (String ex : EXTENSOES_VIDEOS) {
            copiarArquivos(diretorioDeBusca, diretorioDestino, ex);
        }
    }

    public static void moverArquivoTipoFotos(File diretorioDeBusca, File diretorioDestino) {
        for (String ex : EXTENSOES_FOTOS){
            moverArquivos(diretorioDeBusca, diretorioDestino, ex);
        }
    }

    public static void moverArquivoTipoMusica(File diretorioDeBusca, File diretorioDestino) {
        for (String ex : EXTENSOES_MUSICAS) {
            moverArquivos(diretorioDeBusca, diretorioDestino, ex);
        }
    }

    public static void moverArquivoTipoVideo(File diretorioDeBusca, File diretorioDestino) {
        for (String ex : EXTENSOES_VIDEOS){
            moverArquivos(diretorioDeBusca, diretorioDestino, ex);
        }
    }

    public static void criarDiretorio(File file) {

        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public Arquivos(String string) {
        super(string);
    }

}

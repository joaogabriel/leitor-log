package br.jus.tse.eleitoral.leitorlog.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class LogService {

	public static String recuperarArquivoLog(String urlArquivoLog) {
		try {
			// Encapsula a URL num objeto java.net.URL
			URL url = new URL(urlArquivoLog);

			// Queremos o arquivo local com o mesmo nome descrito na URL
			// Lembrando que o URL.getPath() ira retornar a estrutura
			// completa de diretorios e voce deve tratar esta String
			// caso nao deseje preservar esta estrutura no seu disco local.
			// String nomeArquivoLocal = url.getPath();

			// Cria streams de leitura (este metodo ja faz a conexao)...
			InputStream is = url.openStream();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			// Le e grava byte a byte. Voce pode (e deve) usar buffers para
			// melhor performance (BufferedReader).
			int umByte = 0;
			while ((umByte = is.read()) != -1) {
				baos.write(umByte);
			}

			// Nao se esqueca de sempre fechar as streams apos seu uso!
			is.close();
			baos.close();
			
			// apos criar o arquivo fisico, retorna referencia para o mesmo
//			new File(diretorioGravacaoArquivo + nomeArquivoLocal);
			return baos.toString();
		} catch (Exception e) {
			// Lembre-se de tratar bem suas excecoes, ou elas tambem lhe
			// tratarão mal!
			// Aqui so vamos mostrar o stack no stderr.
			e.printStackTrace();
		}

		return null;
	}
	
	public static void recuperarArquivoLog(String urlArquivoLog, String diretorioGravacaoArquivo) {
		try {
			// Encapsula a URL num objeto java.net.URL
			URL url = new URL(urlArquivoLog);

			// Queremos o arquivo local com o mesmo nome descrito na URL
			// Lembrando que o URL.getPath() ira retornar a estrutura
			// completa de diretorios e voce deve tratar esta String
			// caso nao deseje preservar esta estrutura no seu disco local.
			// String nomeArquivoLocal = url.getPath();
			String nomeArquivoLocal = "/server.log";

			// Cria streams de leitura (este metodo ja faz a conexao)...
			InputStream is = url.openStream();

			// ... e de escrita.
			FileOutputStream fos = new FileOutputStream(diretorioGravacaoArquivo + nomeArquivoLocal);

			// Le e grava byte a byte. Voce pode (e deve) usar buffers para
			// melhor performance (BufferedReader).
			int umByte = 0;
			while ((umByte = is.read()) != -1) {
				fos.write(umByte);
			}

			// Nao se esqueca de sempre fechar as streams apos seu uso!
			is.close();
			fos.close();

			// apos criar o arquivo fisico, retorna referencia para o mesmo
			new File(diretorioGravacaoArquivo + nomeArquivoLocal);
		} catch (Exception e) {
			// Lembre-se de tratar bem suas excecoes, ou elas tambem lhe
			// tratarão mal!
			// Aqui so vamos mostrar o stack no stderr.
			e.printStackTrace();
		}

	}
	
}

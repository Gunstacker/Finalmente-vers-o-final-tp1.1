// BIBLIOTECA QUE LÊ O ARQUIVO DE TEXTO
import java.io.BufferedReader;
import java.io.FileReader;

// BIBLIOTECA PARA UTILIZAÇÃO DE TRY CATCH E FINALLY
import java.io.IOException;

//BIBLIOTECAS PARA INCLEMENTAÇÃO DE FILA
import java.util.List;
import java.util.ArrayList;

// BIBLIOTECA QUE IMPRIME OS DADOS EM UMA FORMATAÇÃO DE PAGINA
import javax.swing.JOptionPane;

public class LerArquivoTexto {
	public static void main(String[] args) {
		String imprimir = "Lista de Funcionários\n";
		

		// INSTANCIANDO A LISTA DE FUNCIONARIOS, POR ISSO ESTA NO PLURAL
		List<Funcionario> funcionarios = new ArrayList<>();

		
	
		
		try {
			
			
			// ALTERAR DIREOTORIO NO DIA DA APRESENTAR TRABALHO
			BufferedReader leitor = new BufferedReader(new FileReader(
					"/home/puc/Downloads/Final-version-tp1-main/vers-o-de-ajuste-final-tp1-main/Trabalho tp1 ListaDeFuncionarios/src/folhaFuncionario.txt"));
			String linha;
			
			

			// ENQUANTO TIVEREM LINHAS A SER LIDAS ELE CONTINUA NESTE LOOP
			while (leitor.ready()) {
				// linha vai receber oque o leitor esta lendo por cada linha
				linha = leitor.readLine();

				String[] campos = linha.split("-");
				int numCampos = campos.length;
				
				//linha que havia usado so como teste ppara verificar quantos filhos possuiam por linha
				int numFilhos_linha = (numCampos - 4) / 3;

				// PEGA OS DADOS DO FUNCIONARIO
				int matricula = Integer.parseInt(campos[0]);
				String nome = campos[1];
				double salario = Double.parseDouble(campos[2]) / 100.0; // TRANSFORMAR O VALOR PARA O PADRÃO EXIGIDO
				double gratificacao = Double.parseDouble(campos[3]) / 100.0; // TRANSFORMAR O VALOR PARA O PADRÃO EXIGIDO
																				

				ArrayList<Filhos> filhosFuncionario = new ArrayList<>(); // lista de filhos do funcionario atual
				Filhos filhos; // Instanciando a classe Filhos

				//armazenando dados de cada filho caso haja
				for (int i = 4; i < campos.length; i += 3) {
					String nomeFilho = campos[i];
					String dataNasc = campos[i + 1];
					String sexo = campos[i + 2];

					//passando os dados para a classe filho
					filhos = new Filhos(nomeFilho, dataNasc, sexo);
					//array list dos filhos do funcionario atual que esta sendo armazenando filhos no arraylist
					filhosFuncionario.add(filhos);
				}

				// INSTANCIA DE DADOS DE CADA FUNCIONARIO
				Funcionario funcionario = new Funcionario(matricula, nome, salario, gratificacao, filhosFuncionario);
				// ADICIONA UM FUNCIONARIO NA LISTA TODA LINHA LIDA
				funcionarios.add(funcionario);

			}
			
			
			leitor.close();
			
		} catch (IOException e) {
			// captura um execeção de erro caso ocorra algo ao tentar encontrar o arquivo
			JOptionPane.showMessageDialog(null, e);
		}
		int aux; 
		String sequenciaInicial;
		
		do {
			// solicita a sequência inicial ao usuário
			sequenciaInicial = JOptionPane.showInputDialog("Digite a sequência inicial (entre 1 e 98):");
			aux = Integer.parseInt(sequenciaInicial);

						
		}while(aux <= 1 || aux >= 99);
		
					
			// contador utilizado para sempre aumentar o numero da sequencia
			int contador = 0;
			
			//passando por cada funcionario salvo e armazenando em forma de concatenação os dados que deverão ser impressos
			for (Funcionario funcionario : funcionarios) {
				// numero da sequencia com zeros a esquerda
				int seqInt = Integer.parseInt(sequenciaInicial) + contador;
				//passando o valor de seqint para string
				String seqString = String.valueOf(seqInt);
				//imprimindo dados com o metodo criado em funcionario e com seu respectivo parametro de sequencia transformado em formato de string
				imprimir += funcionario.imprimirDadosFuncionario(String.format("%03d", Integer.parseInt(seqString)));
				contador++;
			}
			System.out.println(imprimir);
			JOptionPane.showMessageDialog(null, imprimir);

		}
	}


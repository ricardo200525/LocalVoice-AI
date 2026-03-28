import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class whisperService {

    private final String executavelWhisper = "C:\\whisper-bin-x642\\Release\\whisper-cli.exe";
    private final String caminhoModelo = "C:\\whisper-bin-x642\\ggml-tiny.bin";

    public String transcrever(File arquivoAudio) {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    executavelWhisper,
                    "-m", caminhoModelo,
                    "-f", arquivoAudio.getAbsolutePath(),
                    "-l", "pt",
                    "-otxt" // Diz ao Whisper para criar um arquivo de texto com a resposta
            );

            builder.directory(new File("C:\\whisper-bin-x642\\Release"));

            Process processo = builder.start();
            processo.waitFor(); // Espera a IA terminar de escrever o arquivo

            // O Whisper sempre cria o texto com o mesmo nome do áudio + ".txt"
            String caminhoArquivoTexto = arquivoAudio.getAbsolutePath() + ".txt";
            Path pathTexto = Path.of(caminhoArquivoTexto);

            // Verifica se a IA realmente criou o arquivo de texto
            if (Files.exists(pathTexto)) {
                // Lê todo o conteúdo do arquivo gerado
                String textoTranscrito = Files.readString(pathTexto);
                return textoTranscrito.trim();
            } else {
                return "[Nenhum texto detectado. Verifique se o áudio não está muito baixo.]";
            }

        } catch (Exception e) {
            System.err.println("Erro na transcrição: " + e.getMessage());
            return "Erro ao processar o áudio.";
        }
    }
}
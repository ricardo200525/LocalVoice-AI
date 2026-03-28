import javax.sound.sampled.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        AudioFormat format = new AudioFormat(
                16000,
                16,
                1,
                true,
                false
        );

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Microfone não suportado.");
            return;
        }

        TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);

        line.open(format);
        line.start();

        System.out.println("Gravando Audio por 5 seg...");

        long timestamp = System.currentTimeMillis();
        File rec = new File("record_"+ timestamp + ".wav");

        AudioInputStream ais = new AudioInputStream(line);

        Thread t = new Thread(() -> {
            try {
                AudioSystem.write(ais, AudioFileFormat.Type.WAVE, rec);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        t.start();

        Thread.sleep(5000);

        line.stop();
        line.close();


        System.out.println("Audio gravado com sucesso.");

        t.join();
        System.out.println("Transcrevendo...");

        whisperService whisperService = new whisperService();

        String pergunta = whisperService.transcrever(rec);

        System.out.println("\nVoce disse: " + pergunta);
    OllamaService ollamaService = new OllamaService();

        if (!pergunta.startsWith("[") && !pergunta.isEmpty()) {
            String resposta = ollamaService.perguntar(pergunta);

            // 3. Exibe a resposta final da IA
            System.out.println("\n--- Resposta do Ollama ---");
            System.out.println(resposta);
            System.out.println("--------------------------");
        } else {
            System.out.println("Não foi possível processar a pergunta: " + pergunta);
        }




    }
}
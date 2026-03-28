import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class OllamaService {
    public String perguntar (String pergunta) {
        System.out.println("Pensando na resposta...");

   try {
        String jsonInputString = String.format(
                "{\"model\": \"llama3.2\", \"prompt\": \"%s\", \"stream\": false}",
                pergunta.replace("\"", "\\\"")
        );

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:11434/api/generate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                .build();

            HttpResponse<String>  response = client.send(request,HttpResponse.BodyHandlers.ofString());
               String jsonReponse = response.body();

       String marcador = "\"response\":\"";
       int inicio = jsonReponse.indexOf(marcador) + marcador.length();
       int fim = jsonReponse.indexOf("\",\"done\"", inicio);
       if (inicio > marcador.length() - 1 ) {
           String  respostaLimpa = jsonReponse.substring(inicio,fim);
           return respostaLimpa.replace("\\\\n", "\n").replace("\\\"", "\"");
       }
       return  "ocorreu um erro na resposta";

        }
            catch (Exception e) {System.err.println("[OllamaService] erro ao conectar com IA:" + e.getMessage());}
        return "Verifique se o Ollama está rodando no seu computador.";
    }
}
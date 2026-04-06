# LocalVoice-AI
Um assistente virtual 100% offline construído em Java, integrando reconhecimento de voz (Whisper) e geração de linguagem natural (Llama 3.2 via Ollama).

 Sobre o Projeto
O LocalVoice-AI é uma aplicação Java que atua como uma ponte entre captação de áudio de hardware e modelos de Inteligência Artificial rodando localmente. O objetivo deste projeto é criar um assistente de voz funcional que garanta total privacidade aos dados do usuário, processando tanto a transcrição do áudio quanto a resposta da IA sem necessidade de conexão com a internet.

 Funcionalidades
Captação de Áudio Nativa: Gravação de voz via microfone utilizando a API nativa do Java (javax.sound.sampled).

Transcrição Offline: Integração com o modelo Whisper (OpenAI) via CLI para conversão de Speech-to-Text de forma rápida e precisa.

Inferência Local (LLM): Consumo da API REST local do Ollama para gerar respostas inteligentes utilizando o modelo Llama 3.2.

Execução Assíncrona: Gerenciamento de múltiplas threads para garantir que a gravação do áudio não bloqueie o fluxo principal da aplicação.

 Tecnologias Utilizadas
Linguagem: Java 

Áudio: Java Sound API (TargetDataLine, AudioInputStream)

Comunicação HTTP: java.net.http.HttpClient (nativo do Java)

Modelos de IA:

Ollama (Rodando o modelo llama3.2)

Whisper CLI / ggml (Modelo ggml-tiny.bin)

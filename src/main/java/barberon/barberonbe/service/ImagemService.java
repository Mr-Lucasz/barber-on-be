package barberon.barberonbe.service;

import barberon.barberonbe.model.Imagem;
import barberon.barberonbe.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository imagemRepository;

    public List<Imagem> getAllImagens() {
        return imagemRepository.findAll();
    }

    public Imagem save(MultipartFile image, Long barbeiroId) {
        try {

            File file = convertMultiPartToFile(image);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "https://muawxhxasilmdcsnogzv.supabase.co/storage/v1/object/public/Perfil/img" + file.getName()))
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im11YXd4aHhhc2lsbWRjc25vZ3p2Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY5ODAyOTk1NSwiZXhwIjoyMDEzNjA1OTU1fQ.RNyH-rW4YNGJ1x59cWtiTjCJYLMu8fMSSPW4DGGhsBc")
                    .PUT(HttpRequest.BodyPublishers.ofFile(file.toPath()))
                    .build();

            // Faça a requisição POST
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verifique a resposta
            if (response.statusCode() == 200) {
                // Tudo certo, salve a imagem no banco de dados           
                Imagem imagem = new Imagem();
                imagemRepository.save(imagem);
                return imagem;
            } else {
                throw new RuntimeException("Failed to upload image: " + response.body());
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
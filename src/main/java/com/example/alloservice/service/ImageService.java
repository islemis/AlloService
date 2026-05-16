package com.example.alloservice.service;


import com.example.alloservice.model.*;
import com.example.alloservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageDataRepository imageDataRepository;
    private final OffreRepository offreRepository;

    public ImageData upload(MultipartFile file, Long offreId) throws IOException {
        Offre offre = offreRepository.findById(offreId)
                .orElseThrow(() -> new RuntimeException("Offre not found"));

        ImageData img = new ImageData();
        img.setName(file.getOriginalFilename());
        img.setType(file.getContentType());
        img.setImageData(file.getBytes());
        img.setOffre(offre);

        return imageDataRepository.save(img);
    }

    public ImageData getById(Long id) {
        return imageDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
    }

    public void delete(Long id) {
        imageDataRepository.deleteById(id);
    }
}
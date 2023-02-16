package com.example.CrudAppDemo.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploader
{
    final String DIR = new ClassPathResource("static/assets/img/").getFile().getAbsolutePath();
    public FileUploader() throws IOException {
    }

    public void uploadFile(MultipartFile file, String fileName) {

        try
        {
            Files.copy(file.getInputStream(), Paths.get(DIR + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}

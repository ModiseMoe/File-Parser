package com.enviro.assessment.grad001.ModiseMoerane.FileParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class FileParserImpl implements FileParser {

    private final AccountProfileRepository accountProfileRepository;
    @Value("${csv.file.path}")
    private String csvFile;
    @Value("${image.directory}")
    private String imageDirectory;

    @Autowired //dependency injection
    public FileParserImpl(AccountProfileRepository accountProfileRepository) {
        this.accountProfileRepository = accountProfileRepository;
    }

    @Override
    public void parseCSV(File csvFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                if (fields.length >= 4) {
                    String name = fields[0];
                    String surname = fields[1];
                    String imageFormat = fields[2];
                    String base64ImageData = fields[3];

                    if (StringUtils.hasText(name) && StringUtils.hasText(surname)
                            && StringUtils.hasText(imageFormat) && StringUtils.hasText(base64ImageData)) {

                        // Convert base64 image data to a physical file
                        File imageF = convertCSVDataToImage(base64ImageData, imageFormat);

                        if (imageF != null) {
                            // Create the image link for the REST endpoint
                            URI httpImageLink = createImageLink(imageF);

                            // Create and save the AccountProfile entity
                            AccountProfile accountProfile = new AccountProfile();
                            accountProfile.setAccountHolderName(name);
                            accountProfile.setAccounntHolderSurname(surname);
                            accountProfile.setHttpImageLink(httpImageLink.toString());

                            accountProfileRepository.save(accountProfile);
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Handle exception accordingly
            e.printStackTrace();
        }

    }

    @Override
    public File convertCSVDataToImage(String base64ImageData, String imgFormat) {
        // Generate a unique filename for the image
        String filename = UUID.randomUUID().toString() + "." + imgFormat;

        // Decode the base64 image data
        byte[] imageData = Base64Utils.decodeFromString(base64ImageData);

        try {
            // Create the image file
            File imageFile = new File(imageDirectory, filename);

            // Write the image data to the file
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            outputStream.write(imageData);
            outputStream.close();

            return imageFile;
        } catch (IOException e) {
            // Handle exception accordingly
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public URI createImageLink(File fileImage) {
        try {
            return fileImage.toURI();
        } catch (Exception e) {
            // Handle exception accordingly
            e.printStackTrace();
            return null;
        }

    }
}

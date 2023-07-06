package com.enviro.assessment.grad001.ModiseMoerane.FileParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/images")
public class ImageController {

    private final AccountProfileRepository accountProfileRepository;

    @Autowired
    public ImageController(AccountProfileRepository accountProfileRepository) {
        this.accountProfileRepository = accountProfileRepository;
    }

    @GetMapping("/{name}/{surname}/{imageFileName:.+}")
    public ResponseEntity<FileSystemResource> getHttpImageLink(@PathVariable String name, @PathVariable String surname,
            @PathVariable String imageFileName) {
        // Find the AccountProfile by name and surname
        AccountProfile accountProfile = accountProfileRepository.findByAccountHolderNameAndAccountHolderSurname( name, surname);

        if (accountProfile == null) {
            // Return a 404 Not Found response if the AccountProfile is not found
            return ResponseEntity.notFound().build();
        }

        // Get the image file path based on the accountProfile's httpImageLink
        String imagePath = accountProfile.getHttpImageLink();

        // Create a FileSystemResource from the image file path
        FileSystemResource resource = new FileSystemResource(imagePath);

        if (resource.exists()) {
            // Return the image file as a ResponseEntity with status 200 OK
            return ResponseEntity.ok(resource);
        } else {
            // Return a 404 Not Found response if the image file doesn't exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

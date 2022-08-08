package com.amenity_reservation_system.configration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Configuration
public class PhotoStorageCloudinary {

    private String urlDefaultPhoto; 

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "amenity-service",
                "api_key", "411664439325612",
                "api_secret", "rkwGDljyAhZlO6syekDkX6C84Rw",
                "secure", true));
    }

    @Bean
    public String urlDefaultPhoto() throws IOException {
        if (urlDefaultPhoto == null) {
            File file = new File("src/main/resources/defaultPhoto/defaultPhoto.png");

            Map uploadResult = cloudinary().uploader()
                    .upload(file, ObjectUtils.asMap("folder", "App Amenity Reservation System/Default IMG"));
            urlDefaultPhoto = String.valueOf(uploadResult.get("url"));
        }
        return urlDefaultPhoto;
    }
}

package com.amenity_reservation_system.configration;

import com.amenity_reservation_system.dao.AmenityTypeRepository;
import com.amenity_reservation_system.dao.ReservationRepository;
import com.amenity_reservation_system.dao.UserRepository;
import com.amenity_reservation_system.entity.AmenityType;
import com.amenity_reservation_system.entity.Reservation;
import com.amenity_reservation_system.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class UploadDataToDB {

    private final String urlDefaultPhoto;
    private final PasswordEncoder passwordEncoder;

    public UploadDataToDB(String urlDefaultPhoto, PasswordEncoder passwordEncoder) {
        this.urlDefaultPhoto = urlDefaultPhoto;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, AmenityTypeRepository amenityTypeRepository,
                                      ReservationRepository reservationRepository) {
        return (args) -> {
            userRepository.save(User.builder()
                    .id(1L)
                    .username("admin")
                    .password(passwordEncoder.encode("admin753"))
                    .fullName("Main Supervisor")
                    .build());

            userRepository.save(User.builder()
                    .username("andrey")
                    .password(passwordEncoder.encode("12345"))
                    .fullName("Andrey Yatskiv")
                    .build());
            userRepository.save(User.builder()
                    .username("igor")
                    .password(passwordEncoder.encode("12345"))
                    .fullName("Igor Koshevenko")
                    .build());
            userRepository.save(User.builder()
                    .username("mikhail")
                    .password(passwordEncoder.encode("12345"))
                    .fullName("Mikhail Chumakov")
                    .build());


            amenityTypeRepository.save(AmenityType.builder()
                    .amenityName("GYM")
                    .capacity(20)
                    .urlPhoto("https://res.cloudinary.com/amenity-service/image/upload/v1659933024/App%20Amenity%20Reservation%20System/gym_m_mfhlaw.jpg")
                    .apiTemperature("http://amenity.hopto.org:8086/query?db=telegraf&epoch=ms&q=SELECT+value+FROM+mqtt_consumer+limit+1")
                    .build());
            amenityTypeRepository.save(AmenityType.builder()
                    .amenityName("POOL")
                    .capacity(4)
                    .urlPhoto("https://res.cloudinary.com/amenity-service/image/upload/v1659933024/App%20Amenity%20Reservation%20System/pool_m_xlywwo.jpg")
                    .apiTemperature("http://amenity.hopto.org:8086/query?db=telegraf&epoch=ms&q=SELECT+value*0.95+FROM+mqtt_consumer+limit+1")
                    .build());
            amenityTypeRepository.save(AmenityType.builder()
                    .amenityName("SAUNA")
                    .capacity(1)
                    .urlPhoto("https://res.cloudinary.com/amenity-service/image/upload/v1659933024/App%20Amenity%20Reservation%20System/sauna_m_s8ujnz.jpg")
                    .apiTemperature("http://amenity.hopto.org:8086/query?db=telegraf&epoch=ms&q=SELECT+value*2+FROM+mqtt_consumer+limit+1")
                    .build());

            reservationRepository.save(Reservation.builder()
                    .amenityType(amenityTypeRepository.findFirstByAmenityName("SAUNA"))
                    .reservationDate(LocalDate.now().minusDays(2))
                    .startTime(LocalTime.of(8, 0))
                    .endTime(LocalTime.of(10, 30))
                    .user(userRepository.findFirstByUsername("andrey"))
                    .build());

            reservationRepository.save(Reservation.builder()
                    .amenityType(amenityTypeRepository.findFirstByAmenityName("SAUNA"))
                    .reservationDate(LocalDate.now().minusDays(1))
                    .startTime(LocalTime.of(8, 0))
                    .endTime(LocalTime.of(10, 30))
                    .user(userRepository.findFirstByUsername("igor"))
                    .build());

            reservationRepository.save(Reservation.builder()
                    .amenityType(amenityTypeRepository.findFirstByAmenityName("SAUNA"))
                    .reservationDate(LocalDate.now())
                    .startTime(LocalTime.of(8, 0))
                    .endTime(LocalTime.of(10, 30))
                    .user(userRepository.findFirstByUsername("mikhail"))
                    .build());
            reservationRepository.save(Reservation.builder()
                    .amenityType(amenityTypeRepository.findFirstByAmenityName("SAUNA"))
                    .reservationDate(LocalDate.now().plusDays(1))
                    .startTime(LocalTime.of(8, 0))
                    .endTime(LocalTime.of(10, 30))
                    .user(userRepository.findFirstByUsername("andrey"))
                    .build());
            reservationRepository.save(Reservation.builder()
                    .amenityType(amenityTypeRepository.findFirstByAmenityName("SAUNA"))
                    .reservationDate(LocalDate.now().plusDays(2))
                    .startTime(LocalTime.of(8, 0))
                    .endTime(LocalTime.of(10, 30))
                    .user(userRepository.findFirstByUsername("igor"))
                    .build());
        };
    }
}

package org.example.propertyview.repository;

import org.example.propertyview.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    // Поиск отелей по параметрам
    @Query("SELECT h FROM Hotel h WHERE " +
            "(:name IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :name, '%')) AND " +
            "(:brand IS NULL OR LOWER(h.brand) LIKE LOWER(CONCAT('%', :brand, '%')) AND " +
            "(:city IS NULL OR LOWER(h.address.city) = LOWER(:city)) AND " +
            "(:country IS NULL OR LOWER(h.address.country) = LOWER(:country))")
    List<Hotel> searchHotels(
            String name,
            String brand,
            String city,
            String country);

    // Поиск отелей по удобствам
    @Query("SELECT DISTINCT h FROM Hotel h JOIN h.amenities a WHERE a IN :amenities")
    List<Hotel> findByAmenities(List<String> amenities);

    // Гистограмма по брендам
    @Query("SELECT h.brand, COUNT(h) FROM Hotel h GROUP BY h.brand")
    List<Object[]> countHotelsByBrand();

    // Гистограмма по городам
    @Query("SELECT h.address.city, COUNT(h) FROM Hotel h GROUP BY h.address.city")
    List<Object[]> countHotelsByCity();

    // Гистограмма по странам
    @Query("SELECT h.address.country, COUNT(h) FROM Hotel h GROUP BY h.address.country")
    List<Object[]> countHotelsByCountry();

    // Гистограмма по удобствам
    @Query("SELECT a, COUNT(h) FROM Hotel h JOIN h.amenities a GROUP BY a")
    List<Object[]> countAmenities();

    // Преобразование результатов гистограммы в Map
    default Map<String, Long> getHistogramData(String param) {
        List<Object[]> results;

        switch (param.toLowerCase()) {
            case "brand":
                results = countHotelsByBrand();
                break;
            case "city":
                results = countHotelsByCity();
                break;
            case "country":
                results = countHotelsByCountry();
                break;
            case "amenities":
                results = countAmenities();
                break;
            default:
                throw new IllegalArgumentException("Unsupported histogram parameter: " + param);
        }

        return results.stream()
                .collect(Collectors.toMap(
                        obj -> String.valueOf(obj[0]),
                        obj -> (Long) obj[1]
                ));
    }
}
package com.example.Labfinal.Repository;


import com.example.Labfinal.Entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenRepository extends BaseRepository<Imagen, Long> {
    List<Imagen> findByDenominacion(String s);
}

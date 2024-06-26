package com.example.Labfinal;

import com.example.Labfinal.Entity.Imagen;
import com.example.Labfinal.Repository.ImagenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class ImagenRepositoryTest {
    @Autowired
    private ImagenRepository imagenRepository;

    @Test
    public void testGuardarYRecuperarImagen() {
        // Crear un nuevo artículo
        Imagen imagen = new Imagen("Miel");

        // Guardar el artículo en el repositorio
        Imagen imagenGuardado = imagenRepository.save(imagen);

        // Verificar que el artículo ha sido guardado correctamente
        assertThat(imagenGuardado.getId()).isNotNull();

        // Recuperar el artículo por su ID
        Imagen imagenRecuperado = imagenRepository.findById(imagenGuardado.getId()).orElse(null);

        // Verificar que el artículo recuperado es el mismo que el guardado
        assertThat(imagenRecuperado).isNotNull();
        assertThat(imagenRecuperado.getDenominacion()).isEqualTo("Miel");

    }

    @Test
    public void testEncontrarTodosLasImagenes() {
        // Crear y guardar solo las imágenes con denominación "Imagen 1"
        Imagen imagen1 = new Imagen();
        imagen1.setDenominacion("Imagen 1");
        Imagen imagen2 = new Imagen();
        imagen2.setDenominacion("Imagen 1");
        Imagen imagen3 = new Imagen();
        imagen3.setDenominacion("Imagen 1");
        imagenRepository.save(imagen1);
        imagenRepository.save(imagen2);
        imagenRepository.save(imagen3);

        // Buscar imágenes por denominación "Imagen 1"
        List<Imagen> imagenes = imagenRepository.findByDenominacion("Imagen 1");

        // Verificar que se han recuperado las imágenes correctas
        assertThat(imagenes).hasSize(4); // Debes ajustar este tamaño según lo esperado
        assertThat(imagenes).extracting(Imagen::getDenominacion).containsOnly("Imagen 1");
    }
    @Test
    public void testActualizarImagen() {
        // Crear y guardar una imagen
        Imagen imagen = new Imagen();
        imagen.setDenominacion("Imagen 1");
        Imagen imagenGuardada = imagenRepository.save(imagen);

        // Actualizar la imagen
        imagenGuardada.setDenominacion("Imagen 1 Actualizada");
        Imagen imagenActualizada = imagenRepository.save(imagenGuardada);

        // Verificar que la imagen ha sido actualizada correctamente
        assertThat(imagenActualizada.getDenominacion()).isEqualTo("Imagen 1 Actualizada");
    }
    @Test
    public void testEliminarImagen() {
        // Crear y guardar una imagen
        Imagen imagen = new Imagen();
        imagen.setDenominacion("Imagen a eliminar");
        Imagen imagenGuardada = imagenRepository.save(imagen);

        // Eliminar la imagen
        imagenRepository.deleteById(imagenGuardada.getId());

        // Verificar que la imagen ha sido eliminada
        Imagen imagenEliminada = imagenRepository.findById(imagenGuardada.getId()).orElse(null);
        assertThat(imagenEliminada).isNull();
    }
    @Test
    public void testContarImagenes() {
        // Crear y guardar varias imágenes
        Imagen imagen1 = new Imagen();
        imagen1.setDenominacion("Imagen 1");
        Imagen imagen2 = new Imagen();
        imagen2.setDenominacion("Imagen 2");
        imagenRepository.save(imagen1);
        imagenRepository.save(imagen2);

        // Contar el número de imágenes
        long count = imagenRepository.count();

        // Verificar que el conteo es correcto
        assertThat(count).isEqualTo(2);
    }

}

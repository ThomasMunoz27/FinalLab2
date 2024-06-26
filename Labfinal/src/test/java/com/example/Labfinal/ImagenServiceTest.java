package com.example.Labfinal;

import com.example.Labfinal.Entity.Imagen;
import com.example.Labfinal.Repository.ImagenRepository;
import com.example.Labfinal.Service.ImagenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ImagenServiceTest {

    @Mock
    private ImagenRepository imagenRepository;

    @InjectMocks
    private ImagenService imagenService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarImagen() {
        // Configurar el mock para devolver el artículo guardado
        Imagen imagen = new Imagen("Imagen 1");
        when(imagenRepository.save(any(Imagen.class))).thenReturn(imagen);

        // Ejecutar el método del servicio que guarda una imagen
        Imagen imagenGuardada = null;
        try {
            imagenGuardada = imagenService.crear(imagen);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Verificar que la imagen se ha guardado correctamente
        assertThat(imagenGuardada).isNotNull();
        assertThat(imagenGuardada.getDenominacion()).isEqualTo("Imagen 1");

        // Verificar que el método save del repositorio fue llamado
        verify(imagenRepository, times(1)).save(any(Imagen.class));
    }

    @Test
    public void testObtenerImagenPorId() {
        // Configurar el mock para devolver un artículo por su ID
        Imagen imagen = new Imagen("Imagen 1");
        imagen.setId(1L);
        when(imagenRepository.findById(1L)).thenReturn(Optional.of(imagen));

        // Ejecutar el método del servicio que busca una imagen por ID
        Optional<Imagen> imagenRecuperada = null;
        try {
            imagenRecuperada = imagenService.buscarPorId(1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Verificar que la imagen recuperada es la correcta
        assertThat(imagenRecuperada).isPresent();
        assertThat(imagenRecuperada.get().getDenominacion()).isEqualTo("Imagen 1");

        // Verificar que el método findById del repositorio fue llamado
        verify(imagenRepository, times(1)).findById(1L);
    }

    @Test
    public void testObtenerTodasLasImagenes() {
        // Configurar el mock para devolver una lista de imágenes
        Imagen imagen1 = new Imagen("Imagen 1");
        Imagen imagen2 = new Imagen("Imagen 2");
        when(imagenRepository.findAll()).thenReturn(Arrays.asList(imagen1, imagen2));

        // Ejecutar el método del servicio que lista todas las imágenes
        List<Imagen> imagenes = null;
        try {
            imagenes = imagenService.listar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Verificar que todas las imágenes se han recuperado correctamente
        assertThat(imagenes).hasSize(2);
        assertThat(imagenes).extracting(Imagen::getDenominacion).contains("Imagen 1", "Imagen 2");

        // Verificar que el método findAll del repositorio fue llamado
        verify(imagenRepository, times(1)).findAll();
    }

    @Test
    public void testEliminarImagenPorId() {
        // Ejecutar el método del servicio que elimina una imagen por ID
        try {
            imagenService.eliminar(1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Verificar que el método deleteById del repositorio fue llamado con el ID correcto
        verify(imagenRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGuardarImagen_LanzaExcepcion() {
        // Configurar el mock para lanzar una excepción cuando se guarda la imagen
        Imagen imagen = new Imagen("Imagen 1");
        when(imagenRepository.save(any(Imagen.class))).thenThrow(RuntimeException.class);

        // Verificar que la excepción se lanza correctamente al guardar la imagen
        try {
            imagenService.crear(imagen);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(Exception.class);
        }

        // Verificar que el método save del repositorio fue llamado
        verify(imagenRepository, times(1)).save(any(Imagen.class));
    }
}
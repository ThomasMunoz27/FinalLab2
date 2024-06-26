package com.example.Labfinal.Controller;

import com.example.Labfinal.Entity.Imagen;
import com.example.Labfinal.Service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagenes")
public class ImagenController extends BaseController<Imagen,Long>{
    public ImagenController(ImagenService imagenService){
        super(imagenService);
    }
}
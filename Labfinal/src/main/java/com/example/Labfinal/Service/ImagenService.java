package com.example.Labfinal.Service;

import com.example.Labfinal.Entity.Imagen;
import com.example.Labfinal.Repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenService extends BaseService<Imagen,Long> {
    public ImagenService(ImagenRepository imagenRepository){
        super(imagenRepository);
    }

}

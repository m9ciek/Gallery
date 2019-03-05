package com.maciek.gallery.dao;

import com.maciek.gallery.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query("SELECT f FROM Image f WHERE LOWER(f.fileName) = LOWER(:fileName)")
    Image find(@Param("fileName") String fileName);

}

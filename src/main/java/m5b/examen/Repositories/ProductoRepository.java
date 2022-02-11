package m5b.examen.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import m5b.examen.Models.producto;

@Repository
public interface ProductoRepository  extends JpaRepository<producto,Long>{
    
}

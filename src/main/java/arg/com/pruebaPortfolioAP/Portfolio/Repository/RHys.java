package arg.com.pruebaPortfolioAP.Portfolio.Repository;


import arg.com.pruebaPortfolioAP.Portfolio.Entity.Hys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RHys extends JpaRepository<Hys, Integer> {
    Optional<Hys> findByNombre(String nombre);
    public  boolean existsByNombre(String nombre);
}


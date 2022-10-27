package arg.com.pruebaPortfolioAP.Portfolio.Repository;

import arg.com.pruebaPortfolioAP.Portfolio.Entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RProyecto extends JpaRepository<Proyecto, Integer> {

    public Optional<Proyecto> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}

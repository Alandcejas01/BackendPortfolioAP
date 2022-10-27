package arg.com.pruebaPortfolioAP.Portfolio.Service;

import arg.com.pruebaPortfolioAP.Portfolio.Entity.Proyecto;
import arg.com.pruebaPortfolioAP.Portfolio.Repository.RProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SProyecto {

    @Autowired
    RProyecto rProyecto;

    public List<Proyecto> list() {
        return rProyecto.findAll();
    }

    public Optional<Proyecto> getOne(int id){
        return rProyecto.findById(id);
    }

    public Optional<Proyecto> getByNombre(String nombre){
        return rProyecto.findByNombre(nombre);
    }

    public void save(Proyecto proyecto){
        rProyecto.save(proyecto);
    }

    public void delete(int id){
        rProyecto.deleteById(id);
    }

    public boolean existsById(int id){
        return rProyecto.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return rProyecto.existsByNombre(nombre);
    }
}

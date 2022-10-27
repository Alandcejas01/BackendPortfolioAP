package arg.com.pruebaPortfolioAP.Portfolio.Security.Service;

import arg.com.pruebaPortfolioAP.Portfolio.Security.Entity.Rol;
import arg.com.pruebaPortfolioAP.Portfolio.Security.Enums.RolNombre;
import arg.com.pruebaPortfolioAP.Portfolio.Security.Repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    IRolRepository iRolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return iRolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        iRolRepository.save(rol);
    }
}

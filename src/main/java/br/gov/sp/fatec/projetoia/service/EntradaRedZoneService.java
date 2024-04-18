package br.gov.sp.fatec.projetoia.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gov.sp.fatec.projetoia.entity.EntradaRedZoneEntity;
import br.gov.sp.fatec.projetoia.repository.EntradaRedZoneRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EntradaRedZoneService {

    @Autowired
    private EntradaRedZoneRepository entradaRedZoneRepository;

    public List<EntradaRedZoneEntity> getAll() {
        return entradaRedZoneRepository.findAll();
    }

    public Optional<EntradaRedZoneEntity> getById(Long id) {
        return entradaRedZoneRepository.findById(id);
    }

    public EntradaRedZoneEntity insert(EntradaRedZoneEntity entity) {
        entity.setData(LocalDateTime.now());
        return entradaRedZoneRepository.save(entity);
    }

    public void delete(EntradaRedZoneEntity entity) {
        entradaRedZoneRepository.delete(entity);
    }

    public EntradaRedZoneEntity update(Long id, EntradaRedZoneEntity updatedEntity) {
        Optional<EntradaRedZoneEntity> existingEntityOptional = entradaRedZoneRepository.findById(id);

        if (existingEntityOptional.isPresent()) {
            EntradaRedZoneEntity existingEntity = existingEntityOptional.get();
            existingEntity.setData(updatedEntity.getData());
            // Se houver outros campos para atualizar, adicione aqui
            return entradaRedZoneRepository.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entidade de entrada com o ID " + id + " não encontrada");
        }
    }
}

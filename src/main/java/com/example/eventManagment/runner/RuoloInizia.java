package com.example.eventManagment.runner;

import com.example.eventManagment.models.Eruolo;
import com.example.eventManagment.models.Ruolo;
import com.example.eventManagment.repository.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RuoloInizia  implements CommandLineRunner {
    @Autowired
    private RuoloRepository ruoloRepository;

    @Override
    public void run(String... args) throws Exception {
        for (Eruolo ruoloEnum : Eruolo.values()) {
            if (!ruoloRepository.findByNome(ruoloEnum).isPresent()) {
                Ruolo ruolo = new Ruolo();
                ruolo.setNome(ruoloEnum);
                ruoloRepository.save(ruolo);
            }
        }
    }
}

package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dtos.MedicoDto;
import med.voll.api.dtos.MedicoGetResponseDto;
import med.voll.api.models.Medico;
import med.voll.api.models.interfaces.IMedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private IMedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public Medico create(@RequestBody @Valid MedicoDto data) {
        Medico medico = new Medico(data);
        return this.medicoRepository.save(medico);
    }

    @GetMapping
    public List<MedicoGetResponseDto> get() {
        List<Medico> medicos = this.medicoRepository.findAll();
        return medicos.stream().map(medico -> new MedicoGetResponseDto(
            medico.getNome(),
            medico.getEmail(),
            medico.getCrm(),
            medico.getEspecialidade()
        )).toList();
    }

}

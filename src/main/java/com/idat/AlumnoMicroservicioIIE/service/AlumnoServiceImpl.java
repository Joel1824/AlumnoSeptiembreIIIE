package com.idat.AlumnoMicroservicioIIE.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.AlumnoMicroservicioIIE.dto.AlumnoDTO;
import com.idat.AlumnoMicroservicioIIE.model.Alumno;
import com.idat.AlumnoMicroservicioIIE.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	private AlumnoRepository repository;
	
	@Override
	public List<AlumnoDTO> listar() {	
		
		List<AlumnoDTO> listadto = new ArrayList<>();
		AlumnoDTO dto = null;
				
		for (Alumno alumno : repository.findAll()) {
			dto = new AlumnoDTO();
			dto.setApe(alumno.getApellido());
			dto.setNom(alumno.getNombre());
			dto.setCod(alumno.getIdAlumno());
			listadto.add(dto);
		}
		
		return listadto;
	}
	

	@Override
	public AlumnoDTO obtenerId(Integer id) {		
		
		Alumno alumno = repository.findById(id).orElse(null);
		
		AlumnoDTO dto = new AlumnoDTO();
		dto.setApe(alumno.getApellido());
		dto.setNom(alumno.getNombre());
		dto.setCod(alumno.getIdAlumno());
		
		return dto;
		
	}

	
	@Override
	public void guardar(AlumnoDTO alumno) {
		
		Alumno alu = new Alumno();
		alu.setApellido(alumno.getApe());
		alu.setNombre(alumno.getNom());
		alu.setIdAlumno(alumno.getCod());
		
		repository.save(alu);
	}
	

	@Override
	public void eliminar(Integer id) {
		repository.deleteById(id);
	}
	

	@Override
	public void actualizar(AlumnoDTO alumno) {
		
		Alumno alu = new Alumno();
		alu.setApellido(alumno.getApe());
		alu.setNombre(alumno.getNom());
		alu.setIdAlumno(alumno.getCod());
		
		repository.saveAndFlush(alu);
	}

}

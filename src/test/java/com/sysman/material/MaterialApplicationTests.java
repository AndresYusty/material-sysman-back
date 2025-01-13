package com.sysman.material;

import com.sysman.material.dto.MaterialDTO;
import com.sysman.material.entity.Material;
import com.sysman.material.enums.EstadoMaterial;
import com.sysman.material.repository.MaterialRepository;
import com.sysman.material.service.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MaterialApplicationTests {

	@Mock
	private MaterialRepository materialRepository;

	@InjectMocks
	private MaterialService materialService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testObtenerTodos() {
		// Datos simulados
		Material material1 = new Material();
		material1.setId(1L);
		material1.setNombre("Cemento");
		material1.setDescripcion("Construcción");
		material1.setTipo("Tipo1");
		material1.setPrecio(new BigDecimal("100.5"));
		material1.setFechaCompra(LocalDate.now());
		material1.setEstado(EstadoMaterial.DISPONIBLE);
		material1.setNombreCiudad("Ciudad1");
		material1.setDepartamento("Departamento1");

		Material material2 = new Material();
		material2.setId(2L);
		material2.setNombre("Martillo");
		material2.setDescripcion("Herramienta");
		material2.setTipo("Tipo2");
		material2.setPrecio(new BigDecimal("50.0"));
		material2.setFechaCompra(LocalDate.now());
		material2.setEstado(EstadoMaterial.ACTIVO);
		material2.setNombreCiudad("Ciudad2");
		material2.setDepartamento("Departamento2");

		List<Material> materialesSimulados = List.of(material1, material2);

		// Configuración del mock
		Mockito.when(materialRepository.findAll()).thenReturn(materialesSimulados);

		// Ejecución del servicio
		List<MaterialDTO> resultado = materialService.obtenerTodos();

		// Validaciones
		assertEquals(2, resultado.size());
		assertEquals("Cemento", resultado.get(0).getNombre());
		Mockito.verify(materialRepository, Mockito.times(1)).findAll();
	}

	@Test
	void testCrearMaterial() {
		// Datos simulados
		MaterialDTO materialDTO = new MaterialDTO();
		materialDTO.setNombre("Arena");
		materialDTO.setDescripcion("Material para construcción");
		materialDTO.setTipo("Tipo3");
		materialDTO.setPrecio(new BigDecimal("75.0"));
		materialDTO.setFechaCompra(LocalDate.now());
		materialDTO.setEstado("DISPONIBLE");
		materialDTO.setNombreCiudad("Ciudad3");
		materialDTO.setDepartamento("Departamento3");

		Material materialGuardado = new Material();
		materialGuardado.setId(3L);
		materialGuardado.setNombre("Arena");
		materialGuardado.setDescripcion("Material para construcción");
		materialGuardado.setTipo("Tipo3");
		materialGuardado.setPrecio(new BigDecimal("75.0"));
		materialGuardado.setFechaCompra(LocalDate.now());
		materialGuardado.setEstado(EstadoMaterial.DISPONIBLE);
		materialGuardado.setNombreCiudad("Ciudad3");
		materialGuardado.setDepartamento("Departamento3");

		// Configuración del mock
		Mockito.when(materialRepository.save(Mockito.any(Material.class))).thenReturn(materialGuardado);

		// Ejecución del servicio
		MaterialDTO resultado = materialService.crearMaterial(materialDTO);

		// Validaciones
		assertNotNull(resultado.getId());
		assertEquals("Arena", resultado.getNombre());
		Mockito.verify(materialRepository, Mockito.times(1)).save(Mockito.any(Material.class));
	}

	@Test
	void testActualizarMaterial() {
		// Datos simulados
		Material materialExistente = new Material();
		materialExistente.setId(1L);
		materialExistente.setNombre("Cemento");
		materialExistente.setDescripcion("Construcción");
		materialExistente.setTipo("Tipo1");
		materialExistente.setPrecio(new BigDecimal("100.5"));
		materialExistente.setFechaCompra(LocalDate.now());
		materialExistente.setEstado(EstadoMaterial.DISPONIBLE);
		materialExistente.setNombreCiudad("Ciudad1");
		materialExistente.setDepartamento("Departamento1");

		MaterialDTO materialDTO = new MaterialDTO();
		materialDTO.setNombre("Cemento Actualizado");
		materialDTO.setDescripcion("Construcción Actualizada");
		materialDTO.setTipo("Tipo1");
		materialDTO.setPrecio(new BigDecimal("110.0"));
		materialDTO.setFechaCompra(LocalDate.now());
		materialDTO.setEstado("DISPONIBLE");
		materialDTO.setNombreCiudad("Ciudad1");
		materialDTO.setDepartamento("Departamento1");

		Material materialActualizado = new Material();
		materialActualizado.setId(1L);
		materialActualizado.setNombre("Cemento Actualizado");
		materialActualizado.setDescripcion("Construcción Actualizada");
		materialActualizado.setTipo("Tipo1");
		materialActualizado.setPrecio(new BigDecimal("110.0"));
		materialActualizado.setFechaCompra(LocalDate.now());
		materialActualizado.setEstado(EstadoMaterial.DISPONIBLE);
		materialActualizado.setNombreCiudad("Ciudad1");
		materialActualizado.setDepartamento("Departamento1");

		// Configuración del mock
		Mockito.when(materialRepository.findById(1L)).thenReturn(Optional.of(materialExistente));
		Mockito.when(materialRepository.save(Mockito.any(Material.class))).thenReturn(materialActualizado);

		// Ejecución del servicio
		MaterialDTO resultado = materialService.actualizarMaterial(1L, materialDTO);

		// Validaciones
		assertEquals("Cemento Actualizado", resultado.getNombre());
		assertEquals(new BigDecimal("110.0"), resultado.getPrecio());
		Mockito.verify(materialRepository, Mockito.times(1)).save(Mockito.any(Material.class));
	}
	
}

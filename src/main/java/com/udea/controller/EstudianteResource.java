//package com.myfit.nutrifit.web.rest;
//
//import com.codahale.metrics.annotation.Timed;
//import com.myfit.nutrifit.domain.Estudiante;
//
//import com.myfit.nutrifit.repository.EstudianteRepository;
//import com.myfit.nutrifit.repository.search.EstudianteSearchRepository;
//import com.myfit.nutrifit.web.rest.util.HeaderUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.StreamSupport;
//
//import static org.elasticsearch.index.query.QueryBuilders.*;
//
///**
// * REST controller for managing Estudiante.
// */
//@RestController
//@RequestMapping("/api")
//public class EstudianteResource {
//
//    private final Logger log = LoggerFactory.getLogger(EstudianteResource.class);
//
//    private static final String ENTITY_NAME = "estudiante";
//
//    private final EstudianteRepository estudianteRepository;
//
//    private final EstudianteSearchRepository estudianteSearchRepository;
//
//    public EstudianteResource(EstudianteRepository estudianteRepository, EstudianteSearchRepository estudianteSearchRepository) {
//        this.estudianteRepository = estudianteRepository;
//        this.estudianteSearchRepository = estudianteSearchRepository;
//    }
//
//    /**
//     * POST  /estudiantes : Create a new estudiante.
//     *
//     * @param estudiante the estudiante to create
//     * @return the ResponseEntity with status 201 (Created) and with body the new estudiante, or with status 400 (Bad Request) if the estudiante has already an ID
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PostMapping("/estudiantes")
//    @Timed
//    public ResponseEntity<Estudiante> createEstudiante(@RequestBody Estudiante estudiante) throws URISyntaxException {
//        log.debug("REST request to save Estudiante : {}", estudiante);
//        if (estudiante.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new estudiante cannot already have an ID")).body(null);
//        }
//        Estudiante result = estudianteRepository.save(estudiante);
//        estudianteSearchRepository.save(result);
//        return ResponseEntity.created(new URI("/api/estudiantes/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * PUT  /estudiantes : Updates an existing estudiante.
//     *
//     * @param estudiante the estudiante to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated estudiante,
//     * or with status 400 (Bad Request) if the estudiante is not valid,
//     * or with status 500 (Internal Server Error) if the estudiante couldnt be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/estudiantes")
//    @Timed
//    public ResponseEntity<Estudiante> updateEstudiante(@RequestBody Estudiante estudiante) throws URISyntaxException {
//        log.debug("REST request to update Estudiante : {}", estudiante);
//        if (estudiante.getId() == null) {
//            return createEstudiante(estudiante);
//        }
//        Estudiante result = estudianteRepository.save(estudiante);
//        estudianteSearchRepository.save(result);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, estudiante.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * GET  /estudiantes : get all the estudiantes.
//     *
//     * @return the ResponseEntity with status 200 (OK) and the list of estudiantes in body
//     */
//    @GetMapping("/estudiantes")
//    @Timed
//    public List<Estudiante> getAllEstudiantes() {
//        log.debug("REST request to get all Estudiantes");
//        List<Estudiante> estudiantes = estudianteRepository.findAll();
//        return estudiantes;
//    }
//
//    /**
//     * GET  /estudiantes/:id : get the "id" estudiante.
//     *
//     * @param id the id of the estudiante to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the estudiante, or with status 404 (Not Found)
//     */
//    @GetMapping("/estudiantes/{id}")
//    @Timed
//    public ResponseEntity<Estudiante> getEstudiante(@PathVariable Long id) {
//        log.debug("REST request to get Estudiante : {}", id);
//        Estudiante estudiante = estudianteRepository.findOne(id);
//        return new ResponseEntity<>(estudiante, HttpStatus.OK);
//    }
//
//    /**
//     * DELETE  /estudiantes/:id : delete the "id" estudiante.
//     *
//     * @param id the id of the estudiante to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/estudiantes/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
//        log.debug("REST request to delete Estudiante : {}", id);
//        estudianteRepository.delete(id);
//        estudianteSearchRepository.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
//
//    /**
//     * SEARCH  /_search/estudiantes?query=:query : search for the estudiante corresponding
//     * to the query.
//     *
//     * @param query the query of the estudiante search
//     * @return the result of the search
//     */
//    @GetMapping("/_search/estudiantes")
//    @Timed
//    public List<Estudiante> searchEstudiantes(@RequestParam String query) {
//        log.debug("REST request to search Estudiantes for query {}", query);
//        return StreamSupport
//            .stream(estudianteSearchRepository.search(queryStringQuery(query)).spliterator(), false)
//            .collect(Collectors.toList());
//    }
//
//
//}

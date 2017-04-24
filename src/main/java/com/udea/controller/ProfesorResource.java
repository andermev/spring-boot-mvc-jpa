//package com.myfit.nutrifit.web.rest;
//
//import com.codahale.metrics.annotation.Timed;
//import com.myfit.nutrifit.domain.Profesor;
//
//import com.myfit.nutrifit.repository.ProfesorRepository;
//import com.myfit.nutrifit.repository.search.ProfesorSearchRepository;
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
// * REST controller for managing Profesor.
// */
//@RestController
//@RequestMapping("/api")
//public class ProfesorResource {
//
//    private final Logger log = LoggerFactory.getLogger(ProfesorResource.class);
//
//    private static final String ENTITY_NAME = "profesor";
//
//    private final ProfesorRepository profesorRepository;
//
//    private final ProfesorSearchRepository profesorSearchRepository;
//
//    public ProfesorResource(ProfesorRepository profesorRepository, ProfesorSearchRepository profesorSearchRepository) {
//        this.profesorRepository = profesorRepository;
//        this.profesorSearchRepository = profesorSearchRepository;
//    }
//
//    /**
//     * POST  /profesors : Create a new profesor.
//     *
//     * @param profesor the profesor to create
//     * @return the ResponseEntity with status 201 (Created) and with body the new profesor, or with status 400 (Bad Request) if the profesor has already an ID
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PostMapping("/profesors")
//    @Timed
//    public ResponseEntity<Profesor> createProfesor(@RequestBody Profesor profesor) throws URISyntaxException {
//        log.debug("REST request to save Profesor : {}", profesor);
//        if (profesor.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new profesor cannot already have an ID")).body(null);
//        }
//        Profesor result = profesorRepository.save(profesor);
//        profesorSearchRepository.save(result);
//        return ResponseEntity.created(new URI("/api/profesors/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * PUT  /profesors : Updates an existing profesor.
//     *
//     * @param profesor the profesor to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated profesor,
//     * or with status 400 (Bad Request) if the profesor is not valid,
//     * or with status 500 (Internal Server Error) if the profesor couldnt be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/profesors")
//    @Timed
//    public ResponseEntity<Profesor> updateProfesor(@RequestBody Profesor profesor) throws URISyntaxException {
//        log.debug("REST request to update Profesor : {}", profesor);
//        if (profesor.getId() == null) {
//            return createProfesor(profesor);
//        }
//        Profesor result = profesorRepository.save(profesor);
//        profesorSearchRepository.save(result);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, profesor.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * GET  /profesors : get all the profesors.
//     *
//     * @return the ResponseEntity with status 200 (OK) and the list of profesors in body
//     */
//    @GetMapping("/profesors")
//    @Timed
//    public List<Profesor> getAllProfesors() {
//        log.debug("REST request to get all Profesors");
//        List<Profesor> profesors = profesorRepository.findAll();
//        return profesors;
//    }
//
//    /**
//     * GET  /profesors/:id : get the "id" profesor.
//     *
//     * @param id the id of the profesor to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the profesor, or with status 404 (Not Found)
//     */
//    @GetMapping("/profesors/{id}")
//    @Timed
//    public ResponseEntity<Profesor> getProfesor(@PathVariable Long id) {
//        log.debug("REST request to get Profesor : {}", id);
//        Profesor profesor = profesorRepository.findOne(id);
//        return new ResponseEntity<>(profesor, HttpStatus.OK);
//    }
//
//    /**
//     * DELETE  /profesors/:id : delete the "id" profesor.
//     *
//     * @param id the id of the profesor to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/profesors/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id) {
//        log.debug("REST request to delete Profesor : {}", id);
//        profesorRepository.delete(id);
//        profesorSearchRepository.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
//
//    /**
//     * SEARCH  /_search/profesors?query=:query : search for the profesor corresponding
//     * to the query.
//     *
//     * @param query the query of the profesor search
//     * @return the result of the search
//     */
//    @GetMapping("/_search/profesors")
//    @Timed
//    public List<Profesor> searchProfesors(@RequestParam String query) {
//        log.debug("REST request to search Profesors for query {}", query);
//        return StreamSupport
//            .stream(profesorSearchRepository.search(queryStringQuery(query)).spliterator(), false)
//            .collect(Collectors.toList());
//    }
//
//
//}

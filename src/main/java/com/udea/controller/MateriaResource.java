//package com.myfit.nutrifit.web.rest;
//
//import com.codahale.metrics.annotation.Timed;
//import com.myfit.nutrifit.domain.Materia;
//
//import com.myfit.nutrifit.repository.MateriaRepository;
//import com.myfit.nutrifit.repository.search.MateriaSearchRepository;
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
// * REST controller for managing Materia.
// */
//@RestController
//@RequestMapping("/api")
//public class MateriaResource {
//
//    private final Logger log = LoggerFactory.getLogger(MateriaResource.class);
//
//    private static final String ENTITY_NAME = "materia";
//
//    private final MateriaRepository materiaRepository;
//
//    private final MateriaSearchRepository materiaSearchRepository;
//
//    public MateriaResource(MateriaRepository materiaRepository, MateriaSearchRepository materiaSearchRepository) {
//        this.materiaRepository = materiaRepository;
//        this.materiaSearchRepository = materiaSearchRepository;
//    }
//
//    /**
//     * POST  /materias : Create a new materia.
//     *
//     * @param materia the materia to create
//     * @return the ResponseEntity with status 201 (Created) and with body the new materia, or with status 400 (Bad Request) if the materia has already an ID
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PostMapping("/materias")
//    @Timed
//    public ResponseEntity<Materia> createMateria(@RequestBody Materia materia) throws URISyntaxException {
//        log.debug("REST request to save Materia : {}", materia);
//        if (materia.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new materia cannot already have an ID")).body(null);
//        }
//        Materia result = materiaRepository.save(materia);
//        materiaSearchRepository.save(result);
//        return ResponseEntity.created(new URI("/api/materias/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * PUT  /materias : Updates an existing materia.
//     *
//     * @param materia the materia to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated materia,
//     * or with status 400 (Bad Request) if the materia is not valid,
//     * or with status 500 (Internal Server Error) if the materia couldnt be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/materias")
//    @Timed
//    public ResponseEntity<Materia> updateMateria(@RequestBody Materia materia) throws URISyntaxException {
//        log.debug("REST request to update Materia : {}", materia);
//        if (materia.getId() == null) {
//            return createMateria(materia);
//        }
//        Materia result = materiaRepository.save(materia);
//        materiaSearchRepository.save(result);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, materia.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * GET  /materias : get all the materias.
//     *
//     * @param filter the filter of the request
//     * @return the ResponseEntity with status 200 (OK) and the list of materias in body
//     */
//    @GetMapping("/materias")
//    @Timed
//    public List<Materia> getAllMaterias(@RequestParam(required = false) String filter) {
//        if ("aula-is-null".equals(filter)) {
//            log.debug("REST request to get all Materias where aula is null");
//            return StreamSupport
//                .stream(materiaRepository.findAll().spliterator(), false)
//                .filter(materia -> materia.getAula() == null)
//                .collect(Collectors.toList());
//        }
//        log.debug("REST request to get all Materias");
//        List<Materia> materias = materiaRepository.findAll();
//        return materias;
//    }
//
//    /**
//     * GET  /materias/:id : get the "id" materia.
//     *
//     * @param id the id of the materia to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the materia, or with status 404 (Not Found)
//     */
//    @GetMapping("/materias/{id}")
//    @Timed
//    public ResponseEntity<Materia> getMateria(@PathVariable Long id) {
//        log.debug("REST request to get Materia : {}", id);
//        Materia materia = materiaRepository.findOne(id);
//        return new ResponseEntity<>(materia, HttpStatus.OK);
//    }
//
//    /**
//     * DELETE  /materias/:id : delete the "id" materia.
//     *
//     * @param id the id of the materia to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/materias/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteMateria(@PathVariable Long id) {
//        log.debug("REST request to delete Materia : {}", id);
//        materiaRepository.delete(id);
//        materiaSearchRepository.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
//
//    /**
//     * SEARCH  /_search/materias?query=:query : search for the materia corresponding
//     * to the query.
//     *
//     * @param query the query of the materia search
//     * @return the result of the search
//     */
//    @GetMapping("/_search/materias")
//    @Timed
//    public List<Materia> searchMaterias(@RequestParam String query) {
//        log.debug("REST request to search Materias for query {}", query);
//        return StreamSupport
//            .stream(materiaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
//            .collect(Collectors.toList());
//    }
//
//
//}

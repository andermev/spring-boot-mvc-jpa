package com.udea.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udea.model.Aula;
import com.udea.repository.AulaRepository;

/**	
 * REST controller for managing Aula.
 */
@RestController
@RequestMapping("/api")
public class AulaResource {

    private final Logger log = LoggerFactory.getLogger(AulaResource.class);

    private static final String ENTITY_NAME = "aula";

    private final AulaRepository aulaRepository;

//    private final AulaSearchRepository aulaSearchRepository;

    public AulaResource(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
//        this.aulaSearchRepository = aulaSearchRepository;
    }
//
//    /**
//     * POST  /aulas : Create a new aula.
//     *
//     * @param aula the aula to create
//     * @return the ResponseEntity with status 201 (Created) and with body the new aula, or with status 400 (Bad Request) if the aula has already an ID
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PostMapping("/aulas")
//    @Timed
//    public ResponseEntity<Aula> createAula(@RequestBody Aula aula) throws URISyntaxException {
//        log.debug("REST request to save Aula : {}", aula);
//        if (aula.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new aula cannot already have an ID")).body(null);
//        }
//        Aula result = aulaRepository.save(aula);
//        aulaSearchRepository.save(result);
//        return ResponseEntity.created(new URI("/api/aulas/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * PUT  /aulas : Updates an existing aula.
//     *
//     * @param aula the aula to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated aula,
//     * or with status 400 (Bad Request) if the aula is not valid,
//     * or with status 500 (Internal Server Error) if the aula couldnt be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/aulas")
//    @Timed
//    public ResponseEntity<Aula> updateAula(@RequestBody Aula aula) throws URISyntaxException {
//        log.debug("REST request to update Aula : {}", aula);
//        if (aula.getId() == null) {
//            return createAula(aula);
//        }
//        Aula result = aulaRepository.save(aula);
//        aulaSearchRepository.save(result);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aula.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /aulas : get all the aulas.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of aulas in body
     */
//    @GetMapping("/aulas")
    @RequestMapping(value="/aulas", method = RequestMethod.GET)
    public List<Aula> getAllAulas(@RequestParam(required = false) String filter) {
        if ("materia-is-null".equals(filter)) {
            log.debug("REST request to get all Aulas where materia is null");
            return StreamSupport
                .stream(aulaRepository.findAll().spliterator(), false)
                .filter(aula -> aula.getMateria() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Aulas");
        List<Aula> aulas = aulaRepository.findAll();
        return aulas;
    }

//    /**
//     * GET  /aulas/:id : get the "id" aula.
//     *
//     * @param id the id of the aula to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the aula, or with status 404 (Not Found)
//     */
//    @GetMapping("/aulas/{id}")
//    @Timed
//    public ResponseEntity<Aula> getAula(@PathVariable Long id) {
//        log.debug("REST request to get Aula : {}", id);
//        Aula aula = aulaRepository.findOne(id);
//        return new ResponseEntity<>(aula, HttpStatus.OK);
//    }
//
//    /**
//     * DELETE  /aulas/:id : delete the "id" aula.
//     *
//     * @param id the id of the aula to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/aulas/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteAula(@PathVariable Long id) {
//        log.debug("REST request to delete Aula : {}", id);
//        aulaRepository.delete(id);
//        aulaSearchRepository.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
//
//    /**
//     * SEARCH  /_search/aulas?query=:query : search for the aula corresponding
//     * to the query.
//     *
//     * @param query the query of the aula search
//     * @return the result of the search
//     */
//    @GetMapping("/_search/aulas")
//    @Timed
//    public List<Aula> searchAulas(@RequestParam String query) {
//        log.debug("REST request to search Aulas for query {}", query);
//        return StreamSupport
//            .stream(aulaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
//            .collect(Collectors.toList());
//    }


}

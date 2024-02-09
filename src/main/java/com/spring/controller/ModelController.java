package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.exception.TokenException;
import com.spring.models.Anneesortie;
import com.spring.models.Carburant;
import com.spring.models.Categorie;
import com.spring.models.Model;
import com.spring.repository.AnneesortieRepository;
import com.spring.services.AnneesortieService;
import com.spring.services.CarburantService;
import com.spring.services.ModelService;
import com.spring.services.TokenService;
import com.spring.utility.Response;

@RestController
@RequestMapping("/api/v1/models")
@CrossOrigin
public class ModelController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private CarburantService carburantService;
    @Autowired
    private AnneesortieService anneesortieService;

    /*** Liste Model ***/
    @GetMapping
    public ResponseEntity<Response> getAll(@RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            response.setData(modelService.getAllModel());
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        } catch (Exception e) {
            response.setStatus_code("401");
            response.setMessage("Il y a une erreur ");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getModelMarqueByIdModel(@PathVariable Integer id,
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            response.setData(modelService.findByModel(id));
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        } catch (Exception e) {
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping
    public ResponseEntity<Response> saveModel(@RequestBody Model model,
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkRole(authorizationHeader, 10);
            Model modelSave = modelService.saveModel(model);
            response.setStatus_code("200");
            response.setData(modelSave);
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable Integer id,
            @RequestHeader("Authorization") String authorizationHeader) {

        Response response = new Response();
        try {
            tokenService.checkRole(authorizationHeader, 10);
            modelService.deleteModel(id);
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }
    }

    /***** Liste carburant ***/
    @GetMapping("v1/carburants")
    public ResponseEntity<Response> listCarburant(
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            response.setData(carburantService.getAllCarburants());
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        } catch (Exception e) {
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    /**** Supprimer une Carburant ****/
    @DeleteMapping("v1/carburants/{id}")
    public ResponseEntity<Response> deleteCarburant(@PathVariable Long id,
            @RequestHeader("Authorization") String authorizationHeader) {

        Response response = new Response();
        try {
            tokenService.checkRole(authorizationHeader, 10);
            carburantService.deleteCarburant(id);
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }
    }

    /**** Update une carburant ***/
    @PutMapping("v1/carburants/{id}")
    public ResponseEntity<Response> updateCarburant(@RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long id, @RequestBody Carburant updatedCarburant) {
        Response response = new Response();
        try {
            tokenService.checkRole(authorizationHeader, 10);
            response.setData(carburantService.updateCarburant(id, updatedCarburant));
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }

    }

    /***** Ajout carbutant ****/
    @PostMapping("v1/carburants")
    public ResponseEntity<Response> saveCarburant(@RequestBody Carburant carburant,
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkRole(authorizationHeader, 10);
            response.setStatus_code("200");
            response.setData(carburantService.saveCarburant(carburant));
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        } catch (Exception e) {
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    /**** Liste de Detail Model /categorie / marque ****/
    @GetMapping("v1/details")
    public ResponseEntity<Response> listDeitailModel(
            @RequestHeader("Authorization") String authorizationHeader, @RequestParam("marque_id") String marque_id,
            @RequestParam("categorie_id") String categorie_id) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            response.setData(modelService.getListDetailModel(marque_id, categorie_id));
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        } catch (Exception e) {
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    /**** Liste annee par model ****/
    @GetMapping("v1/details/{id}")
    public ResponseEntity<Response> listDeitailModelAnnee(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable String id) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            Model model = new Model();
            model.setId_model(id);
            response.setData(modelService.getListAnneModel(model));
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        } catch (Exception e) {
            response.setStatus_code("400");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    /**** Liste Boite de vitesse ****/
    @GetMapping("v1/boitevitesses")
    public ResponseEntity<Response> listBoiteVitesse(
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkSansRole(authorizationHeader);
            response.setStatus_code("200");
            response.setData(modelService.getListeBoiteVitesse());
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        } catch (Exception e) {
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    /***** Insertion anneesortie ****/
    @PostMapping("v1/annees")
    public ResponseEntity<Response> saveCategory(@RequestBody Anneesortie anneesortie,
            @RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            tokenService.checkRole(authorizationHeader, 10);
            Anneesortie annee = anneesortieService.saveAnneesortie(anneesortie);
            response.setStatus_code("200");
            response.setData(annee);
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }
    }

    /**** Supression d'anneesortie *****/
    @DeleteMapping("v1/annees/{id}")
    public ResponseEntity<Response> deleteAnneeSortie(@PathVariable Long id,
            @RequestHeader("Authorization") String authorizationHeader) {

        Response response = new Response();
        try {
            tokenService.checkRole(authorizationHeader, 10);
            anneesortieService.deleteAnnesortie(id);
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (TokenException e) {
            response.setStatus_code(e.getStatus_code());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatus());
        }
    }

}

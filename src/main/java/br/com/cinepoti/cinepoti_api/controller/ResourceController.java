package br.com.cinepoti.cinepoti_api.controller;


import br.com.cinepoti.cinepoti_api.dto.request.ResourceRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.ResourceResponseDTO;
import br.com.cinepoti.cinepoti_api.service.ResourceService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recurso")
@CrossOrigin
public class ResourceController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService){
        this.resourceService = resourceService;
    }

    @GetMapping
    public ResponseEntity<List<ResourceResponseDTO>> getAllResources(){
        List<ResourceResponseDTO> resources = resourceService.getAllResources();
        return ResponseEntity.ok(resources);

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ResourceResponseDTO> createResource(@Valid  @RequestBody ResourceRequestDTO resourceRequestDTO){
        ResourceResponseDTO resource = resourceService.addResource(resourceRequestDTO);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponseDTO> getResourceById(@PathVariable Long id){
        ResourceResponseDTO resourceResponseDTO = resourceService.getResourcesById(id);
        if(resourceResponseDTO != null){
            return ResponseEntity.ok(resourceResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceResponseDTO> updateResource(@PathVariable Long id,
                                                              @RequestBody ResourceRequestDTO resourceRequestDTO){
        ResourceResponseDTO resourceResponseDTO = resourceService.updateResource(id, resourceRequestDTO);
        if(resourceResponseDTO != null){
            return ResponseEntity.ok(resourceResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id){
        resourceService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

package restful;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
@RestController
@RequestMapping(path = "/JSOEntry")
public class RController  {

    Logger log = LoggerFactory.getLogger(RController.class);
    @Autowired
    private JSOService service;
    @Autowired
    private FileStorageService fileStorageService;
    
    @PostMapping(path="/add", produces="application/json", consumes="application/json")
    public ResponseEntity<Boolean> post( @RequestBody RequestBean input) {
        log.info(input.toString());
        return new ResponseEntity<Boolean>(service.save(input), HttpStatus.OK);
    }    
    @DeleteMapping(path="/delete/{firstName}/{lastName}", produces="application/json")
    public ResponseEntity<Boolean> delete(@PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) {
        
        return new ResponseEntity<Boolean>(service.delete(firstName,lastName), HttpStatus.OK);
    }
    @GetMapping(path="/get/{firstName}/{lastName}", produces="application/json")
    public ResponseEntity<List<ResponseBean>> get(@PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) {
        
        return new ResponseEntity<List<ResponseBean>>(service.get(firstName,lastName), HttpStatus.OK);
    }
    @GetMapping(path="/get/{lastName}", produces="application/json")
    public ResponseEntity<List<ResponseBean>> getList(@PathVariable("lastName") String lastName) {
        
        return new ResponseEntity<List<ResponseBean>>(service.getAll(lastName), HttpStatus.OK);
    }
    @GetMapping(path="/getFirst/{firstname}", produces="application/json")
    public ResponseEntity<List<ResponseBean>> getListByFirstname(@PathVariable("firstname") String firstname) {
        
        return new ResponseEntity<List<ResponseBean>>(service.getAllFirstname(firstname), HttpStatus.OK);
    }
    @GetMapping(path="/getAllCSV", produces="application/json")
    public synchronized ResponseEntity<Resource> getAllCSV(HttpServletRequest request) throws Exception {
        service.getAllCSV();
        return downloadFile("addressbook.csv", request);
    }
    @GetMapping(path="/getAll", produces="application/json")
    public ResponseEntity<List<ResponseBean>> getAll() throws Exception {
        return new ResponseEntity<List<ResponseBean>>(service.getAll(), HttpStatus.OK);
    }
    @GetMapping(path="/loadAll", produces="application/json")
    public synchronized ResponseEntity<Boolean> loadAll() throws Exception {
        service.loadFromCSV();
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
 
    private ResponseEntity<Resource> downloadFile(String filename, HttpServletRequest request) throws Exception {
 
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(filename);
 
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
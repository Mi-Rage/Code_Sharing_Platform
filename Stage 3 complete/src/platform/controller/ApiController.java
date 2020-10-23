package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.repository.Repository;
import platform.util.Util;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    private Repository codeRepository;

    public ApiController() {
    }

    @Autowired
    public ApiController(Repository repository) {
        this.codeRepository = repository;
    }

    @GetMapping(path = "/api/code/{id}", produces = "application/json;charset=UTF-8")
    public Code getApiCode(@PathVariable("id") int id) {
        return codeRepository.getStorage().get(id - 1);
    }

    @GetMapping(path = "/api/code/latest", produces = "application/json;charset=UTF-8")
    public Object[] getApiLatestCode() {
        List<Code> responseCode = new ArrayList<>();
        for (int i = codeRepository.lastIndexRepository(); i >= codeRepository.outputLimitIndex(); i--) {
            Code eachCode = codeRepository.getStorage().get(i);
            responseCode.add(eachCode);
        }
        return responseCode.toArray();
    }


    @PostMapping(path = "/api/code/new", produces = "application/json;charset=UTF-8")
    public String setApiCode(@RequestBody Code newCode) {
        Code responseCode = new Code();
        responseCode.setCode(newCode.getCode());
        responseCode.setTitle("Code");
        responseCode.setDate(Util.getCurrentDateTime());
        codeRepository.getStorage().add(responseCode);
        String response = "{ \"id\" : \"" + codeRepository.getStorage().size() + "\" }";
        return response;
    }

}

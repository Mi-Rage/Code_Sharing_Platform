package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.service.CodeService;
import platform.util.Util;

import java.time.LocalDateTime;


@RestController
public class ApiController {

    private CodeService service;

    public ApiController() {
    }

    @Autowired
    public ApiController(CodeService service) {
        this.service = service;
    }

    @GetMapping(path = "/api/code/{id}", produces = "application/json;charset=UTF-8")
    public Code getApiCode(@PathVariable("id") String id) {
        Code responseCode = service.getCodeFromStorage(id);

        if (responseCode.isViewLimit()) {
            service.updateViewById(id);
        }
        if (responseCode.isTimeLimit()) {
//            LocalDateTime currentTime = LocalDateTime.now();
//            service.updateLocalTimeById(id, currentTime);
            long currentSecond = System.currentTimeMillis();
            service.updateTimeById(id, currentSecond);

        }
        return service.getCodeFromStorage(id);
    }

    @GetMapping(path = "/api/code/latest", produces = "application/json;charset=UTF-8")
    public Object[] getApiLatestCode() {
        return service.getLastCode().toArray();
    }


    @PostMapping(path = "/api/code/new", produces = "application/json;charset=UTF-8")
    public String setApiCode(@RequestBody Code newCode) {
        Code responseCode = new Code();
        responseCode.setCode(newCode.getCode());
        responseCode.setTitle("Code");
//        responseCode.setId(Util.getNewUUID());
        responseCode.setTime(newCode.getTime());
        responseCode.setStartSeconds(System.currentTimeMillis());
        responseCode.setStartTime(LocalDateTime.now());
        System.out.println(responseCode.getStartSeconds());
        responseCode.setViews(newCode.getViews());
        responseCode.setViewLimit(newCode.getViews() > 0);
        responseCode.setTimeLimit(newCode.getTime() > 0);
        service.addCodeToStorage(responseCode);
        String response = "{ \"id\" : \"" + responseCode.getId() + "\" }";
        return response;
    }

}

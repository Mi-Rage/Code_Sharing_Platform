package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.service.CodeService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HtmlController {

    private CodeService service;

    public HtmlController() {
    }

    @Autowired
    public HtmlController(CodeService service) {
        this.service = service;
    }

    @GetMapping(path = "/code/{id}", produces = "text/html")
    public String getHtmlCode(@PathVariable("id") int id, Model model) {

        Code responseCode = service.getCodeFromStorage(id);
        model.addAttribute("responseCode", responseCode);

        return "code";
    }

    @GetMapping(path = "/code/latest", produces = "text/html")
    public String getHtmlLatestCode(Model model) {
        List<Code> lastCodesStore = new ArrayList<>();

        for (int i = service.lastIdRepository(); i >= service.outputLimitId(); i--) {
            Code eachCode = service.getCodeFromStorage(i);
            lastCodesStore.add(eachCode);
        }
        model.addAttribute("lastCodesStore", lastCodesStore);

        return "lastcodes";
    }


    @GetMapping(path = "/code/new", produces = "text/html")
    public String getHtmlCodeNew() {
        return "newcode";
    }

}



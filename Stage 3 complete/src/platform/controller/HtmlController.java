package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.repository.Repository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HtmlController {

    private Repository codeRepository;

    public HtmlController() {
    }

    @Autowired
    public HtmlController(Repository repository) {
        this.codeRepository = repository;
    }

    @GetMapping(path = "/code/{id}", produces = "text/html")
    public String getHtmlCode(@PathVariable("id") int id, Model model) {

        Code responseCode = codeRepository.getStorage().get(id - 1);
        model.addAttribute("responseCode", responseCode);

        return "code";
    }

    @GetMapping(path = "/code/latest", produces = "text/html")
    public String getHtmlLatestCode(Model model) {
        List<Code> lastCodesStore = new ArrayList<>();

        for (int i = codeRepository.lastIndexRepository(); i >= codeRepository.outputLimitIndex(); i--) {
            Code eachCode = codeRepository.getStorage().get(i);
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



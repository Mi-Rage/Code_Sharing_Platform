package platform.repository;

import org.springframework.stereotype.Component;
import platform.model.Code;

import java.util.ArrayList;
import java.util.List;

@Component
public class Repository {
    List<Code> storage = new ArrayList<>();

    public List<Code> getStorage() {
        return storage;
    }

    public void setStorage(List<Code> storage) {
        this.storage = storage;
    }

    public int lastIndexRepository(){
        return storage.size() - 1;
    }

    public int outputLimitIndex() {
        int NUMBER_ON_PAGE = 10;
        return storage.size() % NUMBER_ON_PAGE == storage.size() ? 0
                : storage.size() % NUMBER_ON_PAGE;
    }
}

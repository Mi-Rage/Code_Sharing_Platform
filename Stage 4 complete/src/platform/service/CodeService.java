package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.exception.CodeNotFoundException;
import platform.model.Code;
import platform.repository.CodeRepository;

import java.util.Optional;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public void addCodeToStorage(Code code) {
        long counter = codeRepository.count() + 1;
        code.setId((int) counter);
        codeRepository.save(code);
    }

    public Code getCodeFromStorage(int id){
        Optional<Code> requiredCode = codeRepository.findById(id);
        if (requiredCode.isEmpty()) {
            throw new CodeNotFoundException();
        } else {
            return requiredCode.get();
        }
    }

    public int lastIdRepository(){
        return (int) codeRepository.count();
    }

    public int outputLimitId() {
        int NUMBER_ON_PAGE = 10;
        return codeRepository.count() % NUMBER_ON_PAGE == (int) codeRepository.count() ? 1
                : (int) (codeRepository.count() % NUMBER_ON_PAGE + 1);
    }
}

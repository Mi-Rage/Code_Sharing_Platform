package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.exception.CodeNotFoundException;
import platform.model.Code;
import platform.repository.CodeRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public void addCodeToStorage(Code code) {
        codeRepository.save(code);
    }

    public Code getCodeFromStorage(String id) {
        Optional<Code> requiredCode = codeRepository.findById(id);
        if (requiredCode.isEmpty()) {
            throw new CodeNotFoundException();
        } else {
            return requiredCode.get();
        }
    }

    public void updateViewById(String id) {
        Code updateCode = getCodeFromStorage(id);
        int views = updateCode.getViews();
        if (views > 0) {
            views--;
            updateCode.setViews(views);
            codeRepository.save(updateCode);
        } else {
            codeRepository.delete(updateCode);
        }
    }

    public void updateTimeById(String id, long currentSecond) {
        Code updateCode = getCodeFromStorage(id);
        long time = updateCode.getTime() - ((currentSecond - updateCode.getStartSeconds())/ 1000);
        if (time > 0) {
            updateCode.setTime(time);
            codeRepository.save(updateCode);
        } else {
            codeRepository.delete(updateCode);
        }
    }

    public void updateLocalTimeById(String id, LocalDateTime currentTime) {
        Code updateCode = getCodeFromStorage(id);
        long secBetween = ChronoUnit.SECONDS.between(updateCode.getStartTime(), currentTime);
        int time = (int) (updateCode.getTime() - secBetween);
        if (time > 0) {
            updateCode.setTime(time);
            codeRepository.save(updateCode);
        } else {
            codeRepository.delete(updateCode);
        }
    }

    public List<Code> getLastCode() {
        List<Code> codesWithoutLimit = new ArrayList<>();
        codeRepository.findAll().forEach(code -> {
            if (!code.isViewLimit() && !code.isTimeLimit()) {
                codesWithoutLimit.add(code);
            }
        });
        List<Code> lastCodes = new ArrayList<>();
        int NUMBER_ON_PAGE = 10;
        int lastIdRepository = codesWithoutLimit.size() - 1;
        int outputLimitId = codesWithoutLimit.size() % NUMBER_ON_PAGE == codesWithoutLimit.size() ? 0
                : codesWithoutLimit.size() % NUMBER_ON_PAGE;
        for (int i = lastIdRepository; i >= outputLimitId; i--) {
            Code eachCode = codesWithoutLimit.get(i);
            lastCodes.add(eachCode);
        }
        return lastCodes;
    }

}

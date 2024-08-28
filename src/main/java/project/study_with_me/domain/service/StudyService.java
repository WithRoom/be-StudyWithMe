package project.study_with_me.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.domain.entity.Study;
import project.study_with_me.domain.repository.StudyRepository;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    @Transactional
    public void test() {
        studyRepository.save(new Study());


    }

    public String getAccessTokenFromKakao(String clientId, String code) {

        return null;
    }

    public HashMap<String, Object> getUserInfo(String accessToken) {


        return null;
    }
}

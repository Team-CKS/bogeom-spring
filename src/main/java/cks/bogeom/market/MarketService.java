package cks.bogeom.market;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MarketService {
    private final MarketJPARepository marketJPARepository;

//    @Transactional
//    public void join(UserRequest.JoinDTO requestDTO) {
//        sameCheckEmail(requestDTO.getEmail());
//
//        requestDTO.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
//        try {
//            userJPARepository.save(requestDTO.toEntity());
//        } catch (Exception e) {
//            throw new Exception500("unknown server error");
//        }
//    }

//    public String login(UserRequest.LoginDTO requestDTO) {
//        User userPS = userJPARepository.findByEmail(requestDTO.getEmail()).orElseThrow(
//                () -> new Exception400("이메일을 찾을 수 없습니다 : "+requestDTO.getEmail())
//        );
//
//        if(!passwordEncoder.matches(requestDTO.getPassword(), userPS.getPassword())){
//            throw new Exception400("패스워드가 잘못입력되었습니다 ");
//        }
//        return JWTProvider.create(userPS);
//    }

}

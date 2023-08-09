package cks.bogeom.market;

import cks.bogeom._core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MarketRestController {
    private final MarketService marketService;

//    @PostMapping("/check")
//    public ResponseEntity<?> check(@RequestBody @Valid UserRequest.EmailCheckDTO emailCheckDTO, Errors errors) {
//        userService.sameCheckEmail(emailCheckDTO.getEmail());
//        return ResponseEntity.ok(ApiUtils.success(null));
//    }

    @GetMapping("/check2/{id}") //테스트용
    public ResponseEntity<?> check2(@PathVariable int id) {
        System.out.println("id : " + id);
        return ResponseEntity.ok(ApiUtils.success(null));
    }
}

package b203.varium.oauth2.controller;

import b203.varium.oauth2.service.OauthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("/oauth")
public class OauthController {

    private final OauthService oauthService;

    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/redirect/{provider}")
    public RedirectView redirectUser(@PathVariable String provider) {
//        System.out.println(provider);
//        System.out.println("code " + code);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/oauth2/authorization/" + provider);

        return redirectView;
    }

    @GetMapping("/callback/{provider}")
    public ResponseEntity<Map<String, Object>> callResp(@RequestParam String code, @PathVariable String provider) {
        log.info(provider + " " + code);

        Map<String, Object> resp = oauthService.getAccessToken(code, provider);
//        System.out.println(resp);
        return ResponseEntity.ok().body(resp);

    }
}

package com.roadtripmaker.api;

import com.roadtripmaker.domain.model.Address;
import com.roadtripmaker.domain.model.Response;
import com.roadtripmaker.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;

@RestController @RequestMapping("/address") @RequiredArgsConstructor public class AddressRessource {
    private final AddressService addressService;

    @PostMapping("/save") public ResponseEntity<Response> saveAddress(@RequestBody @Valid Address address) throws IOException {
        return ResponseEntity.ok(Response.builder().timeStamp(now()).data(Map.of("result", addressService.saveAddress(address)))
                .message("adresse créée").status(CREATED).statusCode(CREATED.value()).build());
    }

}

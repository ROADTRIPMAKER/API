package com.roadtripmaker.api;

import com.roadtripmaker.domain.model.Address;
import com.roadtripmaker.domain.model.Response;
import com.roadtripmaker.service.GeodecodingService;
import com.roadtripmaker.service.GeodecodingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController @RequestMapping("/address") @RequiredArgsConstructor public class AddressRessource {
    private final GeodecodingService geodecoding;

    @PostMapping("/save") public ResponseEntity<Response> getCoordinates(@RequestBody @Valid Address address) {
        return ResponseEntity.ok(
                Response.builder().timeStamp(now()).data(Map.of("results", geodecoding.computeGeoLocation(address)))
                        .message("Position récupérées").status(OK).statusCode(OK.value()).build());

    }
}

package com.epam.esm.controller;

import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.service.api.dto.GiftCertificateDto;
import com.epam.esm.service.api.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.service.api.dto.PaginationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/v1/gift-certificates")
public class GiftCertificateController implements LinkRelationship<GiftCertificateDto> {

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    public List<GiftCertificateDto> findAllGiftCertificates(@RequestParam(required = false) String tagName,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String description,
                                                            @RequestParam(required = false)
                                                                    GiftCertificateQueryParametersDto.TypeSort typeSort,
                                                            @RequestParam(required = false)
                                                                    GiftCertificateQueryParametersDto.OrderSort orderSort,
                                                            @RequestParam(required = false) Integer page,
                                                            @RequestParam(required = false) Integer size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        GiftCertificateQueryParametersDto giftCertificateQueryParametersDto = new GiftCertificateQueryParametersDto();
        giftCertificateQueryParametersDto.setTagName(tagName);
        giftCertificateQueryParametersDto.setName(name);
        giftCertificateQueryParametersDto.setDescription(description);
        giftCertificateQueryParametersDto.setTypeSort(typeSort);
        giftCertificateQueryParametersDto.setOrderSort(orderSort);
        List<GiftCertificateDto> giftCertificateDtoList = giftCertificateService
                .findAll(giftCertificateQueryParametersDto, paginationDto);
        giftCertificateDtoList.forEach(this::addDependenciesLinks);
        return giftCertificateDtoList;
    }

    @GetMapping("/{id}")
    public GiftCertificateDto findGiftCertificateById(@PathVariable long id) {
        GiftCertificateDto giftCertificateDto = giftCertificateService.findById(id);
        addDependenciesLinks(giftCertificateDto);
        return giftCertificateDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateDto addGiftCertificate(@RequestBody GiftCertificateDto giftCertificateDto) {
        GiftCertificateDto giftCertificateDtoResult = giftCertificateService.add(giftCertificateDto);
        return giftCertificateDtoResult;
    }

    @PutMapping(value = "/{id}")
    public GiftCertificateDto updateGiftCertificate(@PathVariable long id,
                                                    @RequestBody GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.setId(id);
        GiftCertificateDto giftCertificateDtoResult = giftCertificateService.updatePart(giftCertificateDto);
        addDependenciesLinks(giftCertificateDto);
        return giftCertificateDtoResult;
    }

    @PatchMapping(value = "/{id}")
    public GiftCertificateDto updatePartOfGiftCertificate(@PathVariable long id,
                                                          @RequestBody GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.setId(id);
        GiftCertificateDto giftCertificateDtoResult = giftCertificateService.updatePart(giftCertificateDto);
        addDependenciesLinks(giftCertificateDto);
        return giftCertificateDtoResult;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGiftCertificate(@PathVariable long id) {
        giftCertificateService.remove(id);
    }

    @Override
    public void addDependenciesLinks(GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.add(linkTo(methodOn(GiftCertificateController.class)
                .findGiftCertificateById(giftCertificateDto.getId())).withSelfRel());
        giftCertificateDto.add(linkTo(methodOn(GiftCertificateController.class)
                .updateGiftCertificate(giftCertificateDto.getId(), giftCertificateDto)).withRel("update"));
        giftCertificateDto.add(linkTo(methodOn(GiftCertificateController.class)
                .updatePartOfGiftCertificate(giftCertificateDto.getId(), giftCertificateDto)).withRel("update-part"));
        giftCertificateDto.getTags().forEach(tagDto -> tagDto.add(linkTo(methodOn(TagController.class)
                .findTagById(tagDto.getId())).withSelfRel()));
    }
}

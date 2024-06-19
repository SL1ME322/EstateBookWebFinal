package com.example.estatebookweb.controllers;

import com.example.estatebookweb.models.EstateModel;
import com.example.estatebookweb.models.TagModel;
import com.example.estatebookweb.repositories.EstateRepository;
import com.example.estatebookweb.repositories.TagRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estates")
public class EstateController {

    private final EstateRepository estateRepository;
    private final TagRepository tagRepository;

    //private static final Logger logger = LoggerFactory.getLogger(EstateController.class);
    public EstateController(EstateRepository estateRepository, TagRepository tagRepository) {
        this.estateRepository = estateRepository;
        this.tagRepository = tagRepository;
    }

    //@GetMapping("/html")
    //public String getHome(Model model) {
    //    List<EstateModel> estates = estateRepository.findAll();
    //    model.addAttribute("estates", estates);
    //    return "html/mainPage";
    //}

    /**
     * Метод для вывода всех объявлений
     *

     * @return
     */
//    @GetMapping("/allEstates")
//    @ResponseBody
//    public List<EstateModel> getAllEstates(
//            @RequestParam(value = "estateType", required = false) String estateType,
//            @RequestParam(value = "minPrice", required = false) Integer minPrice,
//            @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
//            @RequestParam(value = "city", required = false) String city,
//            Model model) {
//        try {
//            List<EstateModel> estates;
//            if (estateType != null && minPrice != null && maxPrice != null) {
//
//                estates = estateRepository.findByEstateTypeAndPriceBetween(estateType, minPrice, maxPrice);
//            } else if (estateType != null) {
//
//                estates = estateRepository.findByEstateType(estateType);
//            } else if (city != null) {
//
//                estates = estateRepository.findByCity(city);
//            }
//                else   {
//
//                estates = estateRepository.findAll();
//            }
//            model.addAttribute("estates", estates);
//            model.addAttribute("estatesSize", estates.size());
//            //logger.info("Retrieved all estates successfully");
//            return estates;
//        } catch (Exception e) {
//            //logger.error("Exception occurred while retrieving all estates", e);
//            System.out.println("Exception:" + e);
//            return new ArrayList<>(); // Вернуть пустой список в случае ошибки
//        }
//    }

    @GetMapping("/allEstates")
    @ResponseBody
    public List<EstateModel> getAllEstatesByCity(@RequestParam(value = "city") String city) {
        try {
            List<EstateModel> estates;
            estates = estateRepository.findByCity(city);
            return estates;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            return new ArrayList<>();
        }

    }
    @GetMapping("/getEstatesOnMainPage")
    @ResponseBody
    public List<EstateModel> getAllEstates (@RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "items_per_range", defaultValue = "1") int itemsPerPage,
                                            @RequestParam(value = "search", required  = false) String search) {
        try {
            List<EstateModel> estates;
            if (search != null && !search.trim().isEmpty()){
                Page<EstateModel> estatePage =  estateRepository.findByAdNameContaining(search, PageRequest.of(page - 1, itemsPerPage));
                estates = estatePage.getContent();

            }
            else{
                estates = estateRepository.findAll();
            }
            return estates;
        } catch (Exception e) {

            System.out.println("Exception:" + e);
            return new ArrayList<>();
        }

    }
    @GetMapping("/getEstatesOnMainPage2")
    @ResponseBody
    public List<EstateModel> getAllEstates2 (@RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "items_per_range", defaultValue = "1") int itemsPerPage,
                                            @RequestParam(value = "search", required  = false) String search) {
        try {
            List<EstateModel> estates;
            if (search != null && !search.trim().isEmpty()){
                Page<EstateModel> estatePage =  estateRepository.findByAdNameContaining(search, PageRequest.of(page - 1, itemsPerPage));
                estates = estatePage.getContent();

            }
            else{
                estates = estateRepository.findAll();
            }
            return estates;
        } catch (Exception e) {

            System.out.println("Exception:" + e);
            return new ArrayList<>();
        }

    }
    /**
     * Метод для получения объявления по id
     *
     * @param id
     * @return estateRepository.findById(id)
     */
    @RequestMapping("/getEstateById/{id}")
    public Optional<EstateModel> getEstateById(@PathVariable Long id) {
        // logger.info("Retrieving estate by ID: {}", id);
        return estateRepository.findById(id);
    }

    /**
     * Метод для создания тэга
     *
     * @param tagModel
     * @param bindingResult
     * @return
     */
    @PostMapping("/createTag")
    public ResponseEntity<String> createTag(@Valid @RequestBody TagModel tagModel,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));
            // logger.error("Validation error occurred while creating tag: {}", errorMessage.toString());
            return ResponseEntity.badRequest().body(errorMessage.toString());
        } else {
            tagRepository.save(tagModel);
            //  logger.info("Tag created successfully: {}", tagModel);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tag created successfully");
        }
    }
    @RequestMapping("/createEstate")
    public ResponseEntity<String> createEstate(@RequestBody EstateModel estateModel) {
            estateRepository.save(estateModel);
            return ResponseEntity.status(HttpStatus.OK).body("Estate created successfully");
    }
    @RequestMapping("/updateEstate/{id}")
    public ResponseEntity<String> updateEstate(@PathVariable Long id, @RequestBody EstateModel estateModel) {
        Optional<EstateModel> existingEstateOptional = estateRepository.findById(id);
        if (existingEstateOptional.isPresent()){
            EstateModel existingEstate = existingEstateOptional.get();
            existingEstate.setAdName(estateModel.getAdName());
            existingEstate.setLocation(estateModel.getLocation());
            existingEstate.setPrice(estateModel.getPrice());
            existingEstate.setPriceForMonth(estateModel.getPriceForMonth());
            existingEstate.setMortgagePrice(estateModel.getMortgagePrice());
            existingEstate.setArea(estateModel.getArea());
            existingEstate.setHouseArea(estateModel.getHouseArea());
            existingEstate.setMetroStation(estateModel.getMetroStation());
            existingEstate.setTrainStation(estateModel.getTrainStation());
            existingEstate.setDescription(estateModel.getDescription());
            existingEstate.setAdDate(estateModel.getAdDate());
            existingEstate.setBuildingDate(estateModel.getBuildingDate());
            existingEstate.setStatus(estateModel.getStatus());
            existingEstate.setEstateRating(estateModel.getEstateRating());
            existingEstate.setUser(estateModel.getUser());
            existingEstate.setRenovation(estateModel.getRenovation());
            existingEstate.setRoomAmount(estateModel.getRoomAmount());
            existingEstate.setPurpose(estateModel.getPurpose());
            existingEstate.setPossiblePurpose(estateModel.getPossiblePurpose());
            existingEstate.setBuildingType(estateModel.getBuildingType());
            existingEstate.setAddress(estateModel.getAddress());
            existingEstate.setFloor(estateModel.getFloor());
            existingEstate.setFloorAmount(estateModel.getFloorAmount());
            existingEstate.setFloor(estateModel.getFloor());
            existingEstate.setEntrance(estateModel.getEntrance());
            estateRepository.save(existingEstate);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Estate created successfully");

    }

}
package com.mehedi.couriertrack.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.mehedi.couriertrack.domain.Shift;
import com.mehedi.couriertrack.domain.ShiftRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class ShiftController {
    private final ShiftRepository shiftRepository;

    public ShiftController(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @GetMapping("/shifts")
    public String showShiftList(Model model) {
        model.addAttribute("shifts", shiftRepository.findAll());

        return "shiftlist";
    }

    @GetMapping("/addshift")
    public String addShift(Model model) {
        model.addAttribute("shift", new Shift());

        return "addshift";
    }

    @PostMapping("/saveShift")
    public String saveShift(Shift shift) {
        shiftRepository.save(shift);
        return "redirect:/shifts";
    }
    
    


    
}

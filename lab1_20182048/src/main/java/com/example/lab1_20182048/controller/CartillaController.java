package com.example.lab1_20182048.controller;

import com.example.lab1_20182048.entity.Cartilla;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cartilla")
public class CartillaController {

    @GetMapping(value = {"/formulario", ""})
    public String formulario(Model model) {
        return "newFrm";
    }
    @PostMapping("/config")
    public String configureGame(@RequestParam("tamanio") int tamanio,
                                @RequestParam("cantidad") int cantidad,
                                Model model) {

        model.addAttribute("tamanio", tamanio);
        model.addAttribute("cantidad", cantidad);
        return "bingo";
    }
}

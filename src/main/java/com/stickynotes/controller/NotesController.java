package com.stickynotes.controller;

import com.stickynotes.entity.Notes;
import com.stickynotes.service.NotesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotesController {

    @Autowired
    private NotesService service;

    @RequestMapping("/home")
    public String getWelcomePage(){
        return "index.html";
    }

    @GetMapping("/add")
    public String getAddNotes(Model model){
        model.addAttribute("note",new Notes());
        return "add-note";
    }

    @PostMapping("/submit")
    public String handleFormSubmit(@Valid @ModelAttribute("note") Notes note,BindingResult result, Model model){
        System.out.println("Note :: "+note);
        result.getAllErrors().forEach((e)-> System.out.println(e));
        if (result.hasErrors()){
            return "add-note";
        }
        service.saveNotes(note);
        System.out.println("Note Saved :: Successfully..!!");
        return "redirect:/view-all";
    }

    @GetMapping("/view-all")
    public String viewAllNotes(Model model){
        final List<Notes> allNotes = service.getAllNotes();
        model.addAttribute("notes",allNotes);
        return "view-all";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id,Model model){
        final Notes notes =service.editNotes(id);
        model.addAttribute("note",notes);
        return "update-note";
    }

    @PostMapping("/update/{id}")
    public String handleUpdateForm(@PathVariable("id") Integer id,@Valid @ModelAttribute("note") Notes note,BindingResult result,Model model){
        if (result.hasErrors()){
            return "update-note";
        }
        service.updateNote(note,id);
        model.addAttribute("msg","Form-Updated Successfully..!!");
        return "redirect:/view-all";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id")Integer id){
        service.deleteNote(id);
        return "redirect:/view-all";
    }
}

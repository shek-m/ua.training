package com.example.taxservice.service;

import com.example.taxservice.dto.NoteDTO;
import org.springframework.stereotype.Service;

@Service
public class RegFormService {
    public String inputNote(NoteDTO name) {return name.toString();}
}
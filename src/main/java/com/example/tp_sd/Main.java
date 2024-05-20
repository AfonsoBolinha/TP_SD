package com.example.tp_sd;

import com.example.tp_sd.Tabelas.*;
import com.example.tp_sd.Views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // This means that this class is a Controller
@RequestMapping(path="/")
public class Main {
    @Autowired // This means to get the bean called userRepository
    private AlunoInterface alunoInterface;
    @Autowired
    private CursoInterface cursoInterface;
    @Autowired
    private NotaInterface notaInterface;
    @Autowired
    private ParticipanteInterface participanteInterface;
    @Autowired
    private IdadeMediaInterface idadeMediaInterface;
    @Autowired
    private NAprovacoesInterface naprovacoesInterface;
    @Autowired
    private NotaAlunoCursoInterface notaAlunoCursoInterface;
    @Autowired
    private NParticipantesInterface nparticipantesInterface;
    @Autowired
    private ProvenienciasInterface provenienciasInterface;
    @Autowired
    private CursosfuncionamentoInterface cursosfuncionamentoInterface;

    @GetMapping(path="/Estatisticas")
    public String estatisticas(Model model) {
        return "Estatisticas/LandingEstatisticas";
    }
    @GetMapping(path="/cursos")
    public String cursos(Model model) {
        model.addAttribute("cursos", nparticipantesInterface.findAll());
        return "Estatisticas/Cursos";
    }
    @GetMapping(path="/cursosEmFuncionamento")
    public String cursosEmFuncionamento(Model model) {
        model.addAttribute("cursos", cursosfuncionamentoInterface.findAll());
        return "Estatisticas/CursosEmFuncionamento";
    }
    @GetMapping(path="/horasDosCursos")
    public String horasDosCursos(Model model) {
        model.addAttribute("cursos", cursoInterface.findAll());
        return "Estatisticas/HorasDosCursos";
    }
    @GetMapping(path="/aprovacoesDosCursos")
    public String aprovacoesDosCursos(Model model) {
        model.addAttribute("cursos", naprovacoesInterface.findAll());
        return "Estatisticas/AprovacoesDosCursos";
    }
    @GetMapping(path="/idadeMediaDeCurso")
    public String idadeMediaDeCurso(Model model) {
        model.addAttribute("cursos", idadeMediaInterface.findAll());
        return "Estatisticas/IdadeMediaDeCurso";
    }
    @GetMapping(path="/deOndeVemOsNossosAlunos")
    public String deOndeVemOsNossosAlunos(Model model) {
        model.addAttribute("cursos", provenienciasInterface.findAll());
        return "Estatisticas/DeOndeVemOsNossosAlunos";
    }

}

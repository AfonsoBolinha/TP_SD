package com.example.tp_sd;

import com.example.tp_sd.Tabelas.*;
import com.example.tp_sd.Views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller // This means that this class is a Controller
@RequestMapping(path="/")
public class Main {
    int userStatus = 2;
    int userId=2;
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
    @Autowired
    private ProfessoresInterface professoresInterface;
    @Autowired
    private AlunosInterface alunosInterface;
    @Autowired
    private ProfessorcursoInterface professorcursoInterface;
    @Autowired
    private AlunocursoInterface alunocursoInterface;

    // Para o login
    @Autowired
    private AlunoService alunoService;

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

    @GetMapping(path="/InscreverAluno")
    public String inscreverAluno(Model model) {
        if (userStatus==3){
            model.addAttribute("aluno", new AlunoEntity());
            return "Inscricao/InscreverAluno";
        }
        else{
            return "redirect:/";
        }
    }
    @PostMapping(path="/InscreverAlunoPost")
    public @ResponseBody String inscreverAluno(@RequestParam String nome,
                                             @RequestParam String contacto,
                                             @RequestParam String dataNascimento,
                                             @RequestParam String proviniência) {

        AlunoEntity aluno = new AlunoEntity();
        aluno.setNome(nome);
        aluno.setContacto(contacto);

        // Conversão de dataNascimento de String para Timestamp
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato para data sem hora
            Date parsedDate = dateFormat.parse(dataNascimento);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            aluno.setDataNascimento(timestamp);
        } catch (ParseException e) {
            e.printStackTrace(); // Trate o erro conforme necessário
        }

        aluno.setProviniência(proviniência);
        aluno.setStatus(1);
        alunoInterface.save(aluno);
        return "redirect:/";
    }

    @GetMapping(path="/InscreverCurso")
    public String inscreverCurso(Model model) {
        if (userStatus==3){
            model.addAttribute("curso", new CursoEntity());
            model.addAttribute("professores", professoresInterface.findAll());
            return "Inscricao/InscreverCurso";
        }
        else{
            return "redirect:/";
        }
    }
    @PostMapping(path="/InscreverCursoPost")
    public @ResponseBody String inscreverCurso(@RequestParam String nome,
                                               @RequestParam Integer ano,
                                               @RequestParam Integer idProfessor,
                                               @RequestParam Integer nHoras) {

        CursoEntity curso = new CursoEntity();
        curso.setNome(nome);
        curso.setAno(ano);
        curso.setIdProfessor(idProfessor);
        curso.setnHoras(nHoras);
        cursoInterface.save(curso);

        // Obter o ID do curso recém-criado
        Integer idCurso = curso.getId();
        ParticipanteEntity participante = new ParticipanteEntity();
        participante.setIdCurso(idCurso);
        participante.setIdAluno(idProfessor);
        participanteInterface.save(participante);
        return "redirect:/";
    }

    @GetMapping(path="/InscreverProfessor")
    public String inscreverProfessor(Model model) {
        if (userStatus==3){
            model.addAttribute("professor", new AlunoEntity());
            return "Inscricao/InscreverProfessor";
        }
        else{
            return "redirect:/";
        }
    }
    @PostMapping(path="/InscreverProfessorPost")
    public @ResponseBody String inscreverProfessor(@RequestParam String nome,
                                               @RequestParam String contacto,
                                               @RequestParam String dataNascimento,
                                               @RequestParam String proviniência) {

        AlunoEntity professor = new AlunoEntity();
        professor.setNome(nome);
        professor.setContacto(contacto);
        // Conversão de dataNascimento de String para Timestamp
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato para data sem hora
            Date parsedDate = dateFormat.parse(dataNascimento);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            professor.setDataNascimento(timestamp);
        } catch (ParseException e) {
            e.printStackTrace(); // Trate o erro conforme necessário
        }

        professor.setProviniência(proviniência);
        professor.setStatus(2);
        alunoInterface.save(professor);
        return "redirect:/";
    }

    @GetMapping(path="/MinhasTurmas")
    public String minhasTurmas(Model model) {
        if (userStatus == 2) {
            model.addAttribute("cursos", professorcursoInterface.minhasTurmas(userId));
            return "Professor/MinhasTurmas";
        } else {
            return "redirect:/";
        }
    }

    //ERROR
    @GetMapping("/curso/{idCurso}")
    public String getCurso(@PathVariable("idCurso") int idCurso, Model model) {
        model.addAttribute("alunos", alunocursoInterface.alunos(idCurso));
        return "Professor/Turma";
    }
    //ERROR


    // Login
    // Falta modificar algumas coisas
    @PostMapping(path="/login")
    public String login(@RequestParam("contacto") String contacto,
                        @RequestParam("password") String password,
                        Model model) {
        boolean isAuthenticated = alunoService.authenticate(contacto, password);
        if(isAuthenticated) {
            return "home";
        }
        else {
            model.addAttribute("error", "Invalid contacto ou palavra passe");
            return "index";
        }
    }
}

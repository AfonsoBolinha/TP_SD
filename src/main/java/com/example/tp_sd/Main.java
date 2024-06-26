package com.example.tp_sd;

import com.example.tp_sd.Tabelas.*;
import com.example.tp_sd.Views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller // This means that this class is a Controller
@RequestMapping(path="/")
public class Main {
    int idCurso=0;
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

    // Password encoding
    // Using Bcrypt strength 10
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Para o login
    @Autowired
    private AlunoService alunoService;

    @GetMapping(path="/")
    public String index(Model model) {
        return "index";
    }
    @GetMapping(path="/homeAluno")
    public String homeAluno(Model model) {
        model.addAttribute("userStatus", userStatus);
        return "homeAluno";
    }
    @GetMapping(path="/homeProfessor")
    public String homeProfessor(Model model) {
        model.addAttribute("userStatus", userStatus);
        return "homeProfessor";
    }
    @GetMapping(path="/homeAdmin")
    public String homeAdmin(Model model) {
        model.addAttribute("userStatus", userStatus);
        return "homeAdmin";
    }
    @GetMapping(path="/Turma")
    public String Turma(Model model) {
        return "Professor/Turma";
    }
    @GetMapping(path="/Estatisticas")
    public String estatisticas(Model model) {
        model.addAttribute("userStatus", userStatus);
        return "Estatisticas/LandingEstatisticas";
    }

    @GetMapping(path="/cursos")
    public String cursos(Model model) {
        model.addAttribute("cursos", nparticipantesInterface.findAll());
        return "Estatisticas/Cursos";
    }
    //Rota das estatisticas para aceder a lista de cursos em funcionamento, pode ser acedido por toda a gente
    @GetMapping(path="/cursosEmFuncionamento")
    public String cursosEmFuncionamento(Model model) {
        model.addAttribute("cursos", cursosfuncionamentoInterface.findAll());
        return "Estatisticas/CursosEmFuncionamento";
    }
    //Rota das estatisticas para aceder ao numero de horas de cada curso, pode ser acedido por toda a gente
    @GetMapping(path="/horasDosCursos")
    public String horasDosCursos(Model model) {
        model.addAttribute("cursos", cursoInterface.findAll());
        return "Estatisticas/HorasDosCursos";
    }
    //Rota das estatisticas para aceder ao numero de aprovações de cada curso, pode ser acedido por toda a gente
    @GetMapping(path="/aprovacoesDosCursos")
    public String aprovacoesDosCursos(Model model) {
        model.addAttribute("cursos", naprovacoesInterface.findAll());
        return "Estatisticas/AprovacoesDosCursos";
    }
    //Rota das estatisticas para aceder a lista de idades medias dos cursos, pode ser acedido por toda a gente
    @GetMapping(path="/idadeMediaDeCurso")
    public String idadeMediaDeCurso(Model model) {
        model.addAttribute("cursos", idadeMediaInterface.findAll());
        return "Estatisticas/IdadeMediaDeCurso";
    }

    //Rota das estatisticas para aceder a lista de sitios de onde vem os alunos, pode ser acedido por toda a gente
    @GetMapping(path="/deOndeVemOsNossosAlunos")
    public String deOndeVemOsNossosAlunos(Model model) {
        model.addAttribute("cursos", provenienciasInterface.findAll());
        return "Estatisticas/DeOndeVemOsNossosAlunos";
    }

    @GetMapping(path="/showAdmins")
    public String showAdmins(Model model) {
        List<AlunoEntity> admins = (List<AlunoEntity>) alunoInterface.findByStatus(3);
        model.addAttribute("admins", admins);
        return "Show/showAdmins";
    }

    @GetMapping(path="/showProfessores")
    public String showProfessores(Model model) {
        List<AlunoEntity> professores = (List<AlunoEntity>) alunoInterface.findByStatus(2);
        model.addAttribute("professores", professores);
        return "Show/showProfessores";
    }

    @GetMapping(path="/showCursos")
    public String showCursos(Model model) {
        List<CursoEntity> cursos = (List<CursoEntity>) cursoInterface.findAll();
        model.addAttribute("cursos", cursos);
        return "Show/showCursos";
    }

    // ============================================= Alunos ============================================================
    @GetMapping(path="/showAlunos")
    public String showAlunos(Model model) {
        List<AlunoEntity> alunos = (List<AlunoEntity>) alunoInterface.findByStatus(1);
        model.addAttribute("alunos", alunos);
        return "Show/showAlunos";
    }
    @GetMapping(path="/InscreverAluno")
    public String inscreverAluno(Model model) {
        if (userStatus==3) {
            model.addAttribute("aluno", new AlunoEntity());
            return "Inscricao/InscreverAluno";
        }
        else {
            return"redirect:/";
        }
    }
    //Rota para inscrever um aluno, so pode ser feito por um administrador(Status=3)
    //Recebe informação do aluno(nome, contacto, password, dataNascimento, proviniência)
    @PostMapping(path="/InscreverAlunoPost")
    public String inscreverOuAtualizarAluno(@RequestParam(required = false) Integer id,
                                                @RequestParam String nome,
                                                @RequestParam String contacto,
                                                @RequestParam(required = false) String password,
                                                @RequestParam String dataNascimento,
                                                @RequestParam String proviniência) {

        AlunoEntity aluno;
        if (id != null) {
            // Atualizar professor existente
            Optional<AlunoEntity> optionalAluno = alunoInterface.findById(id);
            if (optionalAluno.isPresent()) {
                aluno = optionalAluno.get();
                optionalAluno.get().setPassword(encoder.encode(password));
            } else {
                return "redirect:/showAlunos";
            }
        } else {
            // Criar novo aluno
            aluno = new AlunoEntity();
            String encodedPassword = encoder.encode(password);
            aluno.setPassword(encodedPassword);
        }
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

        return "redirect:/showAlunos";
    }

    @GetMapping("/deleteAluno/{id}")
    public String deleteAluno(@PathVariable(value = "id") Integer id ) {
        alunoInterface.deleteById(id);
        return "redirect:/showAlunos";
    }

    @GetMapping("/showUpdateAlunoForm{id}")
    public String showUpdateAlunoForm(@PathVariable(value = "id") int id, Model model) {
        Optional<AlunoEntity> optional = alunoInterface.findById(id);
        AlunoEntity aluno = null;
        if(optional.isPresent()){
            aluno = optional.get();
        }
        else {
            throw new RuntimeException("Aluno not found for id :: " + id);
        }
        model.addAttribute("aluno", aluno);
        return "Show/updateAluno";
    }

    // ============================================= Cursos ============================================================

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
    //Rota para inscrever um curso, so pode ser feito por um administrador(Status=3)
    //Recebe informação do curso(nome, ano, idProfessor, nHoras)
    @PostMapping(path="/InscreverCursoPost")
    public String inscreverOuAtualizarCurso(@RequestParam(required = false) Integer id,
                                            @RequestParam String nome,
                                            @RequestParam Integer ano,
                                            @RequestParam Integer idProfessor,
                                            @RequestParam Integer nHoras) {

        CursoEntity curso;
        if (id != null) {
            // Atualizar curso existente
            Optional<CursoEntity> optionalCurso = cursoInterface.findById(id);
            if (optionalCurso.isPresent()) {
                curso = optionalCurso.get();
            } else {
                return "redirect:/showCursos";
            }
        } else {
            // Criar novo curso
            curso = new CursoEntity();
        }
        curso.setNome(nome);
        curso.setAno(ano);
        curso.setIdProfessor(idProfessor);
        curso.setnHoras(nHoras);
        cursoInterface.save(curso);

        return "redirect:/showCursos";
    }

    @GetMapping("/deleteCurso/{id}")
    public String deleteCurso(@PathVariable(value = "id") Integer id ) {
        cursoInterface.deleteById(id);
        return "redirect:/showCursos";
    }

    @GetMapping("/showUpdateCursoForm{id}")
    public String showUpdateCursoForm(@PathVariable(value = "id") int id, Model model) {
        Optional<CursoEntity> optional = cursoInterface.findById(id);
        CursoEntity curso = null;
        if(optional.isPresent()){
            curso = optional.get();
        }
        else {
            throw new RuntimeException("Curso not found for id :: " + id);
        }
        model.addAttribute("curso", curso);
        model.addAttribute("professores", professoresInterface.findAll());
        return "Show/updateCurso";
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
    //Rota para inscrever um professor, so pode ser feito por um administrador(Status=3)
    //Recebe informação do professor(nome, contacto, password, dataNascimento, proviniência)
    @PostMapping(path="/InscreverProfessorPost")
    public String inscreverOuAtualizarProfessor(@RequestParam(required = false) Integer id,
                                                @RequestParam String nome,
                                                @RequestParam String contacto,
                                                @RequestParam(required = false) String password,
                                                @RequestParam String dataNascimento,
                                                @RequestParam String proviniência) {

        AlunoEntity professor;
        if (id != null) {
            // Atualizar professor existente
            Optional<AlunoEntity> optionalProfessor = alunoInterface.findById(id);
            if (optionalProfessor.isPresent()) {
                professor = optionalProfessor.get();
                optionalProfessor.get().setPassword(encoder.encode(password));
            } else {
                return "redirect:/showProfessores";
            }
        } else {
            // Criar novo professor
            professor = new AlunoEntity();
            String encodedPassword = encoder.encode(password);
            professor.setPassword(encodedPassword);
        }
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

        return "redirect:/showProfessores";
    }

    @GetMapping("/deleteProf/{id}")
    public String deleteProf(@PathVariable(value = "id") Integer id ) {
        alunoInterface.deleteById(id);
        return "redirect:/showProfessores";
    }

    @GetMapping("/showUpdateProfessorForm{id}")
    public String showUpdateProfessorForm(@PathVariable(value = "id") int id, Model model) {
        Optional<AlunoEntity> optional = alunoInterface.findById(id);
        AlunoEntity professor = null;
        if(optional.isPresent()){
            professor = optional.get();
        }
        else {
            throw new RuntimeException("Professor not found for id :: " + id);
        }
        model.addAttribute("professor", professor);
        return "Show/updateProfessor";
    }

    @GetMapping(path="/minhasNotas")
    public String minhasNotas(Model model) {
        if (userStatus == 1) {
            model.addAttribute("notas", notaAlunoCursoInterface.minhasNotas(userId));
            return "Aluno/MinhasNotas";
        } else {
            return "redirect:/";
        }
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

    @GetMapping("/darNotas/{idCurso}")
    public String getCurso(@PathVariable("idCurso") Integer idCurso, Model model) {
        this.idCurso = idCurso;
        model.addAttribute("nota", new NotaEntity()); // Ensure a new instance is created
        model.addAttribute("alunos", notaAlunoCursoInterface.findAll());
        return "Professor/Turma";
    }

    @PostMapping(path="/darNotasPost")
    public String darNotas(@RequestParam("idAluno") int idAluno,
                           @RequestParam("nota") int nota,
                           Model model) {
        NotaEntity notaEntity = new NotaEntity();
        notaEntity.setIdAluno(idAluno);
        notaEntity.setIdCurso(idCurso);
        notaEntity.setNota(nota);
        notaInterface.save(notaEntity);
        return "redirect:/darNotas/" + idCurso;
    }



    // Login
    @PostMapping(path="/login")
    public String login(@RequestParam("contacto") String contacto,
                        @RequestParam("password") String password,
                        Model model) {
        boolean isAuthenticated = alunoService.authenticate(contacto, password);
        if(isAuthenticated) {
            userStatus = alunoService.getAluno(contacto).getStatus();
            if (userStatus == 1) {
                userId = alunoService.getAluno(contacto).getId();
                return "homeAluno";
            }
            else if (userStatus == 2) {
                userId = alunoService.getAluno(contacto).getId();
                return "homeProfessor";
            }
            else if (userStatus == 3) {
                userId = alunoService.getAluno(contacto).getId();
                return "homeAdmin";
            }
            else {
                model.addAttribute("error", "Invalid contacto ou palavra passe, error Status");
                return "index";
            }
        }
        else {
            model.addAttribute("error", "Invalid contacto ou palavra passe!!!");
            return "index";
        }
    }
}

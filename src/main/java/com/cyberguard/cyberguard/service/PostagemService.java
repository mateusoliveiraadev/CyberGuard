package com.cyberguard.cyberguard.service;

import com.cyberguard.cyberguard.entity.Postagem;
import com.cyberguard.cyberguard.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostagemService {

    private final PostagemRepository repository;

    @Autowired
    public PostagemService(PostagemRepository repository) {
        this.repository = repository;
    }

    // 🔍 Listar todas as postagens
    public List<Postagem> listarTodas() {
        return repository.findAll();
    }

    // 💾 Salvar uma nova postagem
    public Postagem salvar(Postagem postagem) {
        return repository.save(postagem);
    }

    // 📝 Lógica de Editar / Atualizar (Ajustado para usar apenas o Conteúdo)
    public Postagem atualizar(Long id, Postagem postagemAtualizada) {
        // Busca a postagem existente no banco de dados
        Postagem postagemExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem com ID " + id + " não encontrada"));

        // 👇 CORRIGIDO: Atualiza apenas o conteúdo, que é o que existe na sua Entity
        postagemExistente.setConteudo(postagemAtualizada.getConteudo());

        // Salva as alterações de volta no PostgreSQL
        return repository.save(postagemExistente);
    }

    // ❌ Lógica de Deletar
    public void deletar(Long id) {
        // Verifica se existe antes de tentar deletar
        if (!repository.existsById(id)) {
            throw new RuntimeException("Postagem com ID " + id + " não encontrada");
        }
        repository.deleteById(id);
    }
}
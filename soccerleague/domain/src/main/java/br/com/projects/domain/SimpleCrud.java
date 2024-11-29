package br.com.projects.domain;

/**
 * Métodos padrão para CRUDs.
 * @param <T> é a entidade
 * @param <J> é a PK da entidade
 */
public interface SimpleCrud<T, J>{

    Paged<T> buscarTodos(PageableRequest request);

    T inserir(T obj);

    T atualizar(T obj);

    void remover(J obj);

    T buscar(J obj);
}

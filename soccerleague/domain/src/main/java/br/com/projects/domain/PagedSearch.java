package br.com.projects.domain;

public interface PagedSearch<T>{

    <T> T search(PageableRequest request);
}

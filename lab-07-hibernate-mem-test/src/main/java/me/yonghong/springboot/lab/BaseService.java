package me.yonghong.springboot.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseService<R extends JpaRepository<T, Long>, T> {

    @Autowired
    private R baseRepository;

    public List<T> findAll() {
        return baseRepository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    public List<T> findAll(Example<T> example) {
        return baseRepository.findAll(example);
    }

    public List<T> findAll(Example<T> example, Sort sort) {
        return baseRepository.findAll(example, sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return baseRepository.findAll(example, pageable);
    }

    public List<T> findAllById(Iterable<Long> ids) {
        return baseRepository.findAllById(ids);
    }

    public Optional<T> findById(Long id) {
        return baseRepository.findById(id);
    }

    public Optional<T> findOne(Example<T> example) {
        return baseRepository.findOne(example);
    }

    public T save(T t) {
        return baseRepository.save(t);
    }

    public T saveAndFlush(T t) {
        return baseRepository.saveAndFlush(t);
    }

    public List<T> saveAll(Iterable<T> iterable) {
        return baseRepository.saveAll(iterable);
    }

    public void delete(Long id) {
        baseRepository.deleteById(id);
    }

    public void delete(T t) {
        baseRepository.delete(t);
    }

    public void delete(Iterable<T> iterable) {
        baseRepository.deleteAll(iterable);
    }

    public void deleteInBatch(Iterable<T> iterable) {
        baseRepository.deleteInBatch(iterable);
    }

    public void deleteAll() {
        baseRepository.deleteAll();
    }

    public void deleteAllInBatch() {
        baseRepository.deleteAllInBatch();
    }

    public boolean exists(Example<T> example) {
        return baseRepository.exists(example);
    }

    public long count(Example<T> example) {
        return baseRepository.count(example);
    }

    public void flush() {
        baseRepository.flush();
    }
}
